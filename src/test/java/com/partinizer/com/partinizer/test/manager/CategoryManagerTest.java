package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.business.manager.CategoryManager;
import com.partinizer.data.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by vincent on 30/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class CategoryManagerTest {

    @Autowired
    private CategoryManager categoryManager;

    @Test
    public void getCategoryById(){

        Category category=categoryManager.getCategoryById(1);
        assertNotNull(category.getResources());
    }
}
