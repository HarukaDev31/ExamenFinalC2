package com.example.service;

import com.example.Dto.AttendanceSummary;
import com.example.entity.AttendanceRecords;
import com.example.repository.AttendanceRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendancRecordService {
    @Autowired
    private AttendanceRecordsRepository attendanceRecordRepository;
    public List<AttendanceSummary> getAttendanceSummary(Long employeeId) {
        // Recuperar todos los registros del empleado
        List<AttendanceRecords> records = attendanceRecordRepository.findByEmployeeId(employeeId);

        // Agrupar los registros por fecha
        Map<LocalDate, List<AttendanceRecords>> groupedByDate = records.stream()
                .collect(Collectors.groupingBy(record -> record.getCreatedAt().toLocalDate()));

        // Lista para almacenar los resúmenes de asistencia
        List<AttendanceSummary> summaries = new ArrayList<>();

        // Iterar sobre las fechas agrupadas
        for (Map.Entry<LocalDate, List<AttendanceRecords>> entry : groupedByDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<AttendanceRecords> dayRecords = entry.getValue();

            // Asegurarse de que hay al menos un registro para el día
            if (!dayRecords.isEmpty()) {
                // Ordenar los registros por hora de entrada (createdAt)
                dayRecords.sort(Comparator.comparing(AttendanceRecords::getCreatedAt));

                // Obtener el primer y último registro
                AttendanceRecords firstRecord = dayRecords.get(0);
                AttendanceRecords lastRecord = dayRecords.get(dayRecords.size() - 1);

                // Calcular la diferencia de horas entre la entrada y la salida
                Duration duration = Duration.between((Temporal) firstRecord.getCreatedAt(), (Temporal) lastRecord.getCreatedAt());
                long totalHours = duration.toHours(); // Total de horas entre entrada y salida

                // Crear el resumen para este día
                AttendanceSummary summary = new AttendanceSummary();
                summary.setFirstRecord(firstRecord);
                summary.setLastRecord(lastRecord);
                summary.setTotalHours(totalHours);

                // Añadir el resumen a la lista
                summaries.add(summary);
            }
        }

        return summaries;
    }

}
