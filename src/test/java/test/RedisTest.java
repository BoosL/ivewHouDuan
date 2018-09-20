package test;

import com.bolaa.common.redis.JedisManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Guofangyao~
 * @email guofangyao@vip.qq.com
 * @date 2018/09/10/13:59
 * @Description  redis的测试类
 * @since 1.0.0
 */
public class RedisTest {
    public static void main(String[] args) throws Exception {



        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/redis.xml");

        JedisManager redisUtil = (JedisManager) context.getBean("jedisManager");

        //=====================testString======================
//        redisUtil.set("name", "郭方尧",5000l);
//        redisUtil.set("age", "25",5l);
//        redisUtil.set("address", "重庆双桥",5000l);
//        System.err.println(redisUtil.sGet("741"));
//          Set<String> set1 = new HashSet<String>();
//          set1.add("guo");
//          set1.add("guo1");
//       //   redisUtil.setRemove("741",set1);
//     //     redisUtil.sSetAndTime("741",100l,set1);
//
//
//        System.err.println(redisUtil.getSetSize("741"));
//
//        System.err.println(redisUtil.sGet("741"));


    }

    }


    class User {

        private String name;
        private Integer age;
        private String address;
        private Double classz;
        private Float classz2;

        public User() {
            super();
        }

        public User(String name, Integer age, String address, Double classz,
                    Float classz2) {
            super();
            this.name = name;
            this.age = age;
            this.address = address;
            this.classz = classz;
            this.classz2 = classz2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Double getClassz() {
            return classz;
        }

        public void setClassz(Double classz) {
            this.classz = classz;
        }

        public Float getClassz2() {
            return classz2;
        }

        public void setClassz2(Float classz2) {
            this.classz2 = classz2;
        }

    }