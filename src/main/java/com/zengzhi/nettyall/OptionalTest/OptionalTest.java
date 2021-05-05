package com.zengzhi.nettyall.OptionalTest;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author xiejiawei
 * @Date 2021-05-01
 * @Time 11:35
 */
@Slf4j
public class OptionalTest {

   public static void main (String[] args){
//       User user = new User(null, "张三");
//       Optional<User> optionalUser = Optional.empty();
//       Optional<User> optionalUser = Optional.of(user);
//       Optional<User> optionalUser = Optional.ofNullable(null);
//       System.out.println(optionalUser.orElse(new User(2, "解佳伟")));
//       System.out.println(optionalUser.orElseGet(new Xjw()));
//       boolean present = optionalUser.isPresent();
//
//
//       optionalUser.ifPresent(user1 -> {
//
//       });

//       System.out.println(optionalUser);
//       Optional<String> x = optionalUser.map(User::getName);
//       System.out.println(x.get());


//       User user = null;
//       User user = new User("john@gmail.com", "1234");
//       log.info("Using orElse");
//       User result = Optional.ofNullable(user).orElse(createNewUser());
//       log.info("orElseResult:"+result);
//       log.debug("Using orElseGet");
//       User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
//       log.info("orElseGetResult:"+result2);

//       User user = new User("anna@gmail.com", "1234");
//       String email = Optional.ofNullable(user)
//               .map(u -> u.getId()).orElse("default@gmail.com");
//       System.out.println(email);

//       User user = new User("anna@gmail.com", "1234");
//       user.setPosition("Developer");
//       String position = Optional.ofNullable(user)
//               .flatMap(u -> u.getPosition()).orElse("S");
//
//
//       System.out.println(position);


       User user = new User("anna@gmail.com", "1234");

       String result = Optional.ofNullable(user)
               .flatMap(u -> u.getAddress())
               .flatMap(a -> a.getCountry())
               .map(c -> c.getCode())
               .orElse("default");
       System.out.println(result);
   }
    private static User createNewUser() {
        log.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }


}
