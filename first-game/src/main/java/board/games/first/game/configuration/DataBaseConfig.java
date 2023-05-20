package board.games.first.game.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {


    /**
     * Возвращает конфигурацию основного источника данных.
     * <p/>
     * Т.к. у нас более одного источника данных, конфигурации тоже более одной. Здесь помечаем как основную ту, что
     * "приезжает" из spring boot.
     *
     * @param properties
     *            свойства источника данных, сконфигурированные spring boot
     * @return конфигурацию основного источника данных
     */
    @Bean
    @Primary
    public DataSourceProperties
    dataSourceProperties( final DataSourceProperties properties) {
        return properties;
    }

    /**
     * Создание источника данных к БД, в которой хранится информация системы.
     * <p/>
     * Код взят отсюда
     * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.NonEmbeddedConfiguration} +
     * результат обёрнут в {@link TransactionAwareDataSourceProxy}.
     *
     * @param properties
     *            конфигурация источника данных
     * @return источник данных к БД, в которой хранится информация системы
     */
    @Bean(name = "SqlDataSource")
    public DataSource dataSource(final DataSourceProperties properties) {
        final DataSourceBuilder<?> factory = properties.initializeDataSourceBuilder()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());
        if (properties.getType() != null) {
            factory.type(properties.getType());
        }
        return new TransactionAwareDataSourceProxy(factory.build());
    }

}
