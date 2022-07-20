package learnSpringBoot.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

// trong controller gọi service
// trong service gọi repositories
public interface IStorageService {
    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll();
    public byte[] readFileContent(String fileName);
    public void deleteAllFiles();
}
