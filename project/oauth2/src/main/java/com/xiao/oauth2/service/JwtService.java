//package com.xiao.oauth2.service;
//
//import com.xiao.oauth2.config.JwtConfig;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Date;
//import java.util.Map;
//
//@Service
//public class JwtService {
//    public String createJWT(Map<String, Object> claims, Long jwt_ttl) {
//        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        // 生成JWT的时间
//        long nowMillis = System.currentTimeMillis();
//
//        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式）
//        // Map<String, Object> claims = new HashMap<>();
//        // claims.put("username", "admin");
//        // claims.put("nick_name", "X-rapido");
//
//        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露。它就是你服务端的私钥，在任何场景都不应该流露出去。
//        // 一旦客户端得知这个secret, 那就意味着客户端可以自我签发jwt。
//        SecretKey key = generalKey();
//
//        // 下面就是在为payload添加各种标准声明和私有声明了
//        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
//                .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
//                //.setIssuedAt(now)           // iat: jwt的签发时间
//                .setIssuer("xzf")          // issuer：jwt签发人
//                // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
//                .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥
//
//        // 设置过期时间
//        if (jwt_ttl >= 0) {
//            long expMillis = nowMillis + jwt_ttl;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//        return builder.compact();
//    }
//
//    /**
//     * 由字符串生成加密key
//     */
//    private SecretKey generalKey() {
//        String stringKey = JwtConfig.JWT_SECRET;
//
//        // 本地的密码解码
//        byte[] encodedKey = Base64.decodeBase64(stringKey);
//
//        // 根据给定的字节数组使用AES加密算法构造一个密钥
//        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
//    }
//}
