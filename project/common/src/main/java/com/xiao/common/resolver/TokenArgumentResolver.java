//package com.xiao.common.resolver;
//
//import com.xiao.common.constant.CommonConstant;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Token转CurrentUser
// */
//@Slf4j
//public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
//
//    //用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument。
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//        return false;
//    }
//
//    /**
//     * @param methodParameter       入参集合
//     * @param modelAndViewContainer model 和 view
//     * @param nativeWebRequest      web相关
//     * @param webDataBinderFactory  入参解析
//     * @return 包装对象
//     */
//    @Override
//    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
//                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        String token = request.getHeader(CommonConstant.TOKEN_HEADER);
//        log.info("token" + token);
//        return null;
//    }
//}
