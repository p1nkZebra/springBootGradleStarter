package com.company.springBootTemplate;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest  //jpa configuration run
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //do not replace datasource from db-config.
@ContextConfiguration(classes = App.class)
public abstract class JpaDataConfigIT {

}
