package domain.realizadorDeReporte.CronoInforme;

import domain.realizadorDeReporte.GeneradorDeInforme;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileNotFoundException;

public class JobInforme implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Obtener el JobDataMap
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        // Recuperar los parámetros
        String ruta = dataMap.getString("ruta");
        GeneradorDeInforme generadorDeInforme = new GeneradorDeInforme();
        try {
            generadorDeInforme.generarInforme(ruta);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
