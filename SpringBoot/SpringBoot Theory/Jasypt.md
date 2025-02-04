## Jasypt

- Spring Boot의 환경 변수, 설정파일등에서 중요한 정보를 암호화 하는 것

**✅ 절차**

- Jasypt 암호화 사이트 접속
    - https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
        
        
    - Enter Plain Text to Encrypt : 암호화할 텍스트 입력
    - Select Type of Encryption
        - One Way Encryption
            - 암호화 가능, 복호화 불가능
            - 비밀번호 저장, 해싱시 사용
        - Two Way Encryption
            - 암호화, 복호화 가능
            - 데이터베이스 비밀번호, API키, 민감한 데이터 보호에 사용
    - Enter Secret Key : 암호화에 사용할 Key 입력
- dependency 설정
    
    ```xml
    	implementation 'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5'
    ```
    
- application.properties에 암호화된 설정 적용
    
    ```xml
    spring.datasource.url=jdbc:mariadb://localhost:3306/youtube
    spring.datasource.username=youtube(pw_youtube)
    # spring.datasource.password=pw_youtube
    # Secret Key : example
    spring.datasource.password=ENC(w/qO+oXQk4+YNZpFHxIVSWbcqzsXxV/u)
    ```
    
- secret key 설정
    - 외부 설정파일에 설정
        
        ```xml
        jasypt.encryptor.location=d:/secure/secure.properties
        ```
        
- Config
    
    ```java
    package io.goorm.mini.config;
    
    import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.Properties;
    
    @Configuration
    public class JasyptConfig {
    
        @Value("${jasypt.encryptor.location}")
        //application.properties에 있는 설정
        private String filePath;
    
        @Bean("jasyptStringEncryptor")
        public StandardPBEStringEncryptor stringEncryptor() throws IOException {
    
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    
            Properties properties = new Properties();
            properties.load(new FileInputStream(filePath));
    
            String encryptKey = properties.getProperty("encryption.key");
            encryptor.setPassword(encryptKey);
            encryptor.setAlgorithm("PBEWithMD5AndDES");
    
            return encryptor;
        }
    
    }
    ```