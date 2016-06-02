package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.business.manager.ResourceManager;
import com.partinizer.data.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by vincent on 30/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class ResourceManagerTest {

    @Autowired
    private ResourceManager resourceManager;


    @Test
    public void getAllResources(){
        List<Resource> resources=resourceManager.getAllResources();
        assertNotNull(resources);
    }

    @Test
    public void uploadImage(){


        File fnew=new File("C:/Users/vincent/Pictures/jpeg.jpeg");
        ByteArrayOutputStream baos=null;
        try {
            BufferedImage originalImage= ImageIO.read(fnew);
            baos=new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpeg", baos );
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageInByte=baos.toByteArray();

        /*Resource resource=resourceManager.getResourceRepository().findOne(1L);
        resource.setIcon(imageInByte);
        resourceManager.getResourceRepository().save(resource);*/
    }
}
