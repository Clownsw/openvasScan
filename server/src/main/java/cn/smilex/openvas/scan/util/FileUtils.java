package cn.smilex.openvas.scan.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author smilex
 * @date 2022/11/7/22:58
 * @since 1.0
 */
public final class FileUtils {
    public static void writeString(Path path, String value, StandardOpenOption... openOptions) throws IOException {
        Files.write(path, value.getBytes(StandardCharsets.UTF_8), openOptions);
    }
}
