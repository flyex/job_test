package com.new_fleyex.test_aspect;
import com.new_fleyex.aspect.CountPerform;
import com.new_fleyex.configuration.AspectJavaConfig;
import com.new_fleyex.cut_point.Performance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectJavaConfig.class)
public class CountPerformTest {
    @Autowired
    private Performance performance;

    @Autowired
    private CountPerform countPerform;

    @Test
    public void testCountPerform(){
        performance.perform(0);
        performance.perform(0);
        performance.perform(1);
        performance.perform(1);
        performance.perform(2);
        performance.perform(0);

        assertEquals(3,countPerform.getCount(0));
        assertEquals(2,countPerform.getCount(1));
        assertEquals(1,countPerform.getCount(2));

    }
}
