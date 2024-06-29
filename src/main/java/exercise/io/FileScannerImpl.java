package exercise.io;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class FileScannerImpl implements FileScanner {

	@Override
	public List<String> files(String dir) {
		return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getAbsolutePath)
				.toList();
	}

}
