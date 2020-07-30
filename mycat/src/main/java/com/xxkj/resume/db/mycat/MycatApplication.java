package com.xxkj.resume.db.mycat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

import java.util.*;

@SpringBootApplication
public class MycatApplication {

    public static void main(String[] args) {
        List<aa> ll = new ArrayList();
        ll.add(new aa(){{
            setId(Long.valueOf("111111111111111111"));
            setName("1111");
        }});
        ll.add(new aa(){{
            setId(Long.valueOf("111111111111111111"));
            setName("2222");
        }});
        ll.add(new aa(){{
            setId(Long.valueOf("111111111111111111"));
            setName("333");
        }});
        ll.add(new aa(){{
            setId(Long.valueOf("111111111111111111"));
            setName("444");
        }});
        //SpringApplication.run(MycatApplication.class, args);
        Map<Long, List<String>> map = new IdentityHashMap();
        ll.forEach(x->{
            if(!map.keySet().isEmpty()) {
                Long id = map.keySet().iterator().next();

                if(id.longValue() == x.getId().longValue()) {
                    List<String> cc = new ArrayList<>();
                    cc.addAll(map.get(id));
                    cc.add(x.getName());
                    map.put(x.getId(), cc);
                }
            } else {
                map.put(x.getId(), Arrays.asList(x.getName()));
            }

        });



        if(map.containsKey(Long.valueOf("111111111111111111"))) {
            System.out.println("***");
        }
        System.out.println(map);
        if(Long.valueOf("111111111111111111").equals(Long.valueOf("111111111111111111"))) {
            System.out.println("true");
        }
    }
}
