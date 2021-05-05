package com.ruoyi.project.test.controller;

import com.google.gson.Gson;
import com.ruoyi.project.test.Dao.EmployeeRepository;
import com.ruoyi.project.test.domain.Employee;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/elastisearch")
public class ElastisearchController {

    /*@Autowired
    private JestHttpClient esClient;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("demo")
    public String demo(){
        /*SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //添加查询条件,QueryBuilders组合查询，这里我们可以和上面再kibana中查询语句对比一下
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                //匹配查询条件
                .must(QueryBuilders.matchQuery("spu_name","ZO0403504035"))
                .must(QueryBuilders.matchQuery("status",1)))
                //分页查询，从第0条，查询20条数据
                .from(0).size(20);

        //建立查询
        Search search = new Search.Builder(searchSourceBuilder.toString())
                //这里我们查询shop-trade索引（相当于数据库）
                .addIndex("shop-trade")
                .build();

        //执行查询返回结果
        SearchResult searchResult = esClient.execute(search);
        //将数据转成json格式
        String jsonString = searchResult.getJsonString();

        System.out.println(jsonString);*/

        /*elasticsearchTemplate.*/
        System.out.println("fdfa");
        return "elastiSearch/test";
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public String add() {
        Employee employee = new Employee();
        for(int i=0;i<100;i++){
            employee.setId(""+i);
            employee.setFirstName("东方饭店就"+i);
            employee.setLastName("zh"+i);
            employee.setAge(26+i);
            employee.setAbout("i am in peking"+i);
            employeeRepository.save(employee);
        }
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employeeRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public String update() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public Employee query() {
        Employee accountInfo = employeeRepository.queryEmployeeById("1");
        FuzzyQueryBuilder fuzzyQueryBuilder = new FuzzyQueryBuilder("firstName", "东fdaf");
        Iterable<Employee> employees = employeeRepository.search(fuzzyQueryBuilder);
        employees.forEach(employee->{
            System.err.println(employee.getId()+employee.getFirstName());
        });

        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;

    }
}
