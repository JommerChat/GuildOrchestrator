package dto;

import com.parlantos.guild.dto.SnowflakeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuildDtoTests {

    @Autowired
    private SnowflakeDto snowflakeDto;

    @Test
    private void testGetSnowflakeId() {

    }
}
