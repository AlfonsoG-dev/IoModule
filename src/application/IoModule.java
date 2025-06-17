import java.io.File;
import java.util.List;
import java.nio.file.Path;
import java.util.concurrent.*;

public class IoModule {
    public static void main(String[] args) {
        String localPath = "." + File.separator;
        FileOperations fo = new FileOperations(localPath);
        String searchPath = "D:/Descargas/dependencies/mysql-connector-j-9.1.0/src";

        // Crear FutureTask
        FutureTask<List<Path>> futureCallableList = new FutureTask<>(fo.getCallableListOfFiles(searchPath));

        // Usar un ExecutorService para manejar la ejecución asincrónica
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureCallableList);

        try {
            // Esperar a que la tarea se complete
            List<Path> result = futureCallableList.get();  // Esto bloqueará hasta que el resultado esté disponible

            // Verificar si no hay archivos
            if (result == null || result.isEmpty()) {
                throw new IllegalArgumentException("No files found inside | " + searchPath + " |");
            }

            // Imprimir resultados
            result.forEach(System.out::println);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al ejecutar la tarea: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            // Asegurarse de que el executor se apague correctamente
            executor.shutdown();
        }
    }
}
