package com.example.service;

import com.example.entity.*;
import com.example.exceptions.NotFoundException;
import com.example.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {
    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private PayrollDetailRepository payrollDetailRepository;
    private int[] ctsMonths = {5, 11};
    private int[] gratificationMonths = {7, 12};
    private final List<PaymentTranches> paymentTranches = new ArrayList<>();
    public PayrollService(PayrollRepository payrollRepository, EmployeeRepository employeeRepository, SalaryRepository salaryRepository, SettingRepository settingRepository,
                          PayrollDetailRepository payrollDetailRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
        this.salaryRepository = salaryRepository;
        this.settingRepository = settingRepository;
        this.payrollDetailRepository = payrollDetailRepository;
        paymentTranches.add(new PaymentTranches(1L, "1er Tramo",0, 5, 0.08)
        );
        paymentTranches.add(new PaymentTranches(2L, "2do Tramo",5, 20, 0.14)
        );
        paymentTranches.add(new PaymentTranches(3L, "3er Tramo",20, 35, 0.17)
        );
        paymentTranches.add(new PaymentTranches(4L, "4to Tramo",35, 45, 0.2)
        );
        paymentTranches.add(new PaymentTranches(5L, "5to Tramo",45, 999999, 0.3)
        );
    }
    @Transactional
    public void generatePayroll(int month,int year,String employeeId){
        List<Employee> employees = employeeRepository.findAll();
        if(employeeId!=null ){
            Optional<Employee> employee = employeeRepository.findById(Long.valueOf(employeeId));
            if(employee.isPresent()){
                employees.clear();
                employees.add(employee.get());
            }else{
                throw new NotFoundException("Employee not found with id: "+employeeId);
            }
        }
        employees.forEach(employee ->{
            Salary salary=salaryRepository.findByYearAndMonthAndEmployeeId(year,month,employee.getId()).get(0);
            if(salary==null){
                throw new NotFoundException("Salary not found for employee with id: xd "+employee.getId());
            }
            System.out.printf("Generating payroll for employee with id: %d\n ",employee.getId());
            //sout salary
            System.out.println(salary.getAmount());
            Payroll payroll = new Payroll();
            double total=salary.getAmount();
            payroll.setEmployee(employee);
            payroll.setMonthCreated(month);
            payroll.setYearCreated(year);
            payroll.setTotal(total);
            payroll.setCreated_at(LocalDate.now());

            //save and get the id
            Payroll savedPayroll = payrollRepository.save(payroll);
            List<PayrollDetail> payrollDetails = new ArrayList<>();
            PayrollDetail payrollDetail = new PayrollDetail();
            payrollDetail.setPayroll(savedPayroll);
            payrollDetail.setTypeKey("SALARY");
            payrollDetail.setValue(total);
            payrollDetail.setCreatedAt(LocalDate.now());
            payrollDetails.add(payrollDetail);
            if(employee.getPensionId()!=null) {
                //if pensionId is 1 use setting with key_val afp if 2 use onp
                if (employee.getPensionId() == 1) {
                    List<Setting> afpDiscount = settingRepository.findByKeyVal("AFP");
                    double afp = afpDiscount.get(0).getValue();
                    payrollDetail = new PayrollDetail();
                    payrollDetail.setPayroll(savedPayroll);
                    payrollDetail.setTypeKey("AFP");
                    payrollDetail.setValue(total * afp);
                    payrollDetail.setCreatedAt(LocalDate.now());
                    payrollDetails.add(payrollDetail);
                    total -= total * afp;
                } else {
                    List<Setting> onpDiscount = settingRepository.findByKeyVal("ONP");
                    double onp = onpDiscount.get(0).getValue();
                    payrollDetail = new PayrollDetail();
                    payrollDetail.setPayroll(savedPayroll);
                    payrollDetail.setTypeKey("ONP");
                    payrollDetail.setValue(total * onp);
                    payrollDetail.setCreatedAt(LocalDate.now());
                    payrollDetails.add(payrollDetail);
                    total -= total * onp;

                }
            }
            if(employee.getHasLoan()){
                List<Setting> loanDiscount = settingRepository.findByKeyVal("seguro");
                double loan = loanDiscount.get(0).getValue();
                payrollDetail = new PayrollDetail();
                payrollDetail.setPayroll(savedPayroll);
                payrollDetail.setTypeKey("SEGURO");
                payrollDetail.setValue(total * loan);
                payrollDetail.setCreatedAt(LocalDate.now());
                payrollDetails.add(payrollDetail);
                total -= total * loan;
            }
            if(getCtsAmount(employee,month,year)>0){
                payrollDetail = new PayrollDetail();
                payrollDetail.setPayroll(savedPayroll);
                payrollDetail.setTypeKey("CTS");
                payrollDetail.setValue(getCtsAmount(employee,month,year));
                payrollDetail.setCreatedAt(LocalDate.now());
                payrollDetails.add(payrollDetail);
                total += getCtsAmount(employee,month,year);
            }
            if(getGratificationAmount(employee,month,year)>0){
                payrollDetail = new PayrollDetail();
                payrollDetail.setPayroll(savedPayroll);
                payrollDetail.setTypeKey("GRATIFICATION");
                payrollDetail.setValue(getGratificationAmount(employee,month,year));
                payrollDetail.setCreatedAt(LocalDate.now());
                payrollDetails.add(payrollDetail);
                total += getGratificationAmount(employee,month,year);
            }
            if(getFiveCategoryAmount(employee,month,year)>0){
                payrollDetail = new PayrollDetail();
                payrollDetail.setPayroll(savedPayroll);
                payrollDetail.setTypeKey("FIVE_CATEGORY");
                payrollDetail.setValue(getFiveCategoryAmount(employee,month,year));
                payrollDetail.setCreatedAt(LocalDate.now());
                payrollDetails.add(payrollDetail);
                total-=getFiveCategoryAmount(employee,month,year);
            }
            //save all payroll details
            payrollDetailRepository.saveAll(payrollDetails);
            //update total
            savedPayroll.setTotal(total);
            payrollRepository.save(savedPayroll);
        });
    }
    public double getCtsAmount(Employee employee, int month, int year) {
        // Meses de CTS: mayo (5) y noviembre (11)
        if (month != 5 && month != 11) {
            return 0;
        }

        LocalDate hireDate = LocalDate.parse(employee.getHireDate());
        if (hireDate.isAfter(LocalDate.of(year, month, 1))) {
            // Si fue contratado después del período del CTS, no le corresponde
            return 0;
        }

        // Calcular los meses completos trabajados en el semestre correspondiente
        int startMonth = (month == 5) ? 11 : 5; // Semestre previo
        int startYear = (month == 5) ? year - 1 : year; // Año del inicio del semestre
        int workedMonths = 0;
        double totalSalary = 0;

        for (int m = startMonth; m != month; m = (m % 12) + 1) {
            int currentYear = (m < startMonth) ? year : startYear;

            List<Salary> salaryList = salaryRepository.findByYearAndMonthAndEmployeeId(currentYear, m, employee.getId());
            double salaryAmount = 0;

            if (salaryList != null && !salaryList.isEmpty()) {
                salaryAmount = salaryList.get(0).getAmount();
            }

            totalSalary += salaryAmount;
            workedMonths++;
        }

        // Calcular la remuneración computable
        double averageSalary = totalSalary / workedMonths;
        double gratification = averageSalary / 6;
        double computableRemuneration = averageSalary + gratification;

        // Calcular el CTS proporcional
        return (computableRemuneration * workedMonths) / 12;
    }
    public double getGratificationAmount(Employee employee, int month, int year) {
        //if month is gratification months get employee hire date and get the salary frrom month and year after hire date before month
        if (month == gratificationMonths[0] || month == gratificationMonths[1]) {
            LocalDate hireDate = LocalDate.parse(employee.getHireDate());
            Boolean hasLoan = employee.getHasLoan();

            double total = 0;
            int monthWorked = month - hireDate.getMonthValue();
            for (int i = hireDate.getMonthValue(); i < month; i++) {
                List<Salary> salary = salaryRepository.findByYearAndMonthAndEmployeeId(year, i, employee.getId());
                double amount = salary.get(0).getAmount();
                if (salary == null || amount == 0) {
                    throw new NotFoundException("Salary not found for employee with id: " + employee.getId());
                }
                total += amount;
            }
            total+= total * monthWorked / 6;
            if(hasLoan){
                List<Setting> loanDiscount = settingRepository.findByKeyVal("seguro");
                double loan = loanDiscount.get(0).getValue();
                total+=total*(1.00+loan);
            }
            return total;

        }else {
            return 0;
        }
    }
    public double getFiveCategoryAmount(Employee employee, int month, int year) {
        LocalDate hireDate = LocalDate.parse(employee.getHireDate());
        double totalIncome = 0;
        double previousIncome = 0;

        // Calcular ingresos acumulados hasta el mes especificado
        for (int i = hireDate.getMonthValue(); i <= month; i++) {
            List<Salary> salary = salaryRepository.findByYearAndMonthAndEmployeeId(year, i, employee.getId());
            if (salary == null || salary.isEmpty()) {
                throw new NotFoundException("Salary not found for employee with id: " + employee.getId());
            }
            if (i < month) {
                previousIncome += salary.get(0).getAmount();
            }
            totalIncome += salary.get(0).getAmount();
        }

        // Obtener UIT y calcular deducción
        Setting uitSetting = settingRepository.findByKeyVal("UIT").get(0);
        double uitValue = uitSetting.getValue();
        double deductibleAmount = uitValue * 7; // Deduce 7 UIT según normativa común
        double previousTaxableIncome = Math.max(0, previousIncome - deductibleAmount);
        double currentTaxableIncome = Math.max(0, totalIncome - deductibleAmount);

        // Calcular impuestos acumulados hasta el mes anterior
        double previousTax = calculateTax(previousTaxableIncome, uitValue);

        // Calcular impuestos acumulados hasta el mes actual
        double currentTax = calculateTax(currentTaxableIncome, uitValue);

        // El impuesto a pagar este mes es la diferencia
        double monthlyTax = currentTax - previousTax;

        return monthlyTax;
    }

    // Método auxiliar para calcular impuestos en base a tramos
    private double calculateTax(double taxableIncome, double uitValue) {
        double tax = 0;
        for (PaymentTranches tranche : paymentTranches) {
            if (taxableIncome > tranche.getInfLimit() * uitValue) {
                double upperLimit = Math.min(taxableIncome, tranche.getSupLimit() * uitValue);
                tax += (upperLimit - tranche.getInfLimit() * uitValue) * tranche.getDiscount();
            } else {
                break;
            }
        }
        return tax;
    }
}

