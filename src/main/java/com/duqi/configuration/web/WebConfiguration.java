package com.duqi.configuration.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


  @Bean
  public StringHttpMessageConverter stringConverter() {
    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
  }

  @Bean
  public HttpMessageConverter fastJsonConverter() {
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
    fastJsonConfig.setCharset(Charset.forName("UTF-8"));
    List<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    fastConverter.setSupportedMediaTypes(fastMediaTypes);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    return fastConverter;
  }

//  @Bean
//  HandlerExceptionResolver exceptionResolver() {
//    return (httpServletRequest, httpServletResponse, o, e) -> {
//      httpServletResponse.setStatus(500);
//      ModelAndView mv = new ModelAndView();
//      FastJsonJsonView view = new FastJsonJsonView();
//      Map<String, Object> attributes = new HashMap<String, Object>();
//      attributes.put("exception", e.getClass().getSimpleName());
//      attributes.put("exceptionMessage", e.getMessage());
//      attributes.put("status", false);
//      view.setAttributesMap(attributes);
//      mv.setView(view);
//      return mv;
//    };
//  }

  @Bean
  WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "DELETE", "PUT")  // * 代表所有
            .allowedOrigins("*") // * 代表所有
            .allowedHeaders("*");
            // .exposedHeaders("Authorization") 如果不设置这个属性前端无法通过response header获取到Authorization也就是token
      }
    };
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.clear();
    converters.add(stringConverter());
    converters.add(fastJsonConverter());
  }

  //  /**
  //   * 添加API匹配前缀,这里写在配置文件中
  //   * @param configurer
  //   */
  //  @Override
  //  public void configurePathMatch(PathMatchConfigurer configurer) {
  //    configurer.addPathPrefix("/api",
  //        c -> c.isAnnotationPresent(RestController.class) || c.isAnnotationPresent(
  //            Controller.class));
  //  }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}








