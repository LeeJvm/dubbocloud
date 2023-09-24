package com.gavin.consumer.framework.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17428 on 2023/4/5.
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     *  创建aip应用
     *  通过aipinfo增加api信息
     *  指定暴露的接口路径：basePackage
     *
     */
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo("DUBBOCLOUD SWAGGER UI", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gavin.consumer.rest"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.getParameterList());
    }

    /**
     *  创建aip信息：（这些基本信息会展现在文档页面中）
     *  访问地址：http://ip:port/swagger-ui.html
     * @param title
     * @param version
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {

        return new ApiInfoBuilder()
                .title(title)
                .description("this is about dubbocloud swaggerui")
                .termsOfServiceUrl("https://localhost:8080")
                .contact(new Contact("lisx", "", ""))
                .version(version)
                .build();

    }


    /**
     * 添加head参数配置
     */
    private List<Parameter> getParameterList() {
        ParameterBuilder clientIdTicket = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        clientIdTicket.name("userToken").description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build(); //设置false，表示clientId参数 非必填,可传可不传！
        pars.add(clientIdTicket.build());

        return pars;

    }


}
