package team.star.blog.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

/**
 * @author mystic
 */
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }
}
