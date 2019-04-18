package com.datatech.controller;

import com.datatech.domain.Edges;
import com.datatech.msg.Msg;
import com.datatech.query.GraphQuery;
import com.datatech.utils.Neo4jUtil1;
import com.datatech.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by glin on 2018/11/9 0009.
 */

@CrossOrigin
@Controller
@RequestMapping(value = "/kg")
@Api("测试api")
public class IndexController {


    @Autowired
    private Neo4jUtil1 neo4jUtil;

    @GetMapping("/index1")
    public String index(Model model) {
        return "kg/increaseAndDecrease";
    }

    @GetMapping("/index")
    public String index1(Model model) {
        return "kg/relationforce";
//        return "kg/d3demo_1";
    }
    @ResponseBody
    @GetMapping(value = "/getgraph",produces = "application/json; charset=utf-8")
    public  Map<String, Object>  getgraph(GraphQuery query) {
        R<HashMap<String, Object>> result = new R<HashMap<String, Object>>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        try {
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:Movie)<-[r]-> (m)   RETURN  *  limit 15");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:Movie)<-[r]-> (m)   RETURN  *  ");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:Movie)  RETURN  * ");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH  (n:shkjgl)-[r]- (m)   RETURN  *");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:shkjgl) where n.bill_no='bill_no' RETURN * limit 5");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (a:shkjgl), (n:shkjgl)-[r]-(m)  where n.bill_no='bill_no' and a.bill_no='bill_no' RETURN * ");
           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:xlk)-[r]-> (m)  RETURN * limit 25");
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:shkjgl)-[r]->(m) RETURN * ");

        } catch (Exception e) {
            e.printStackTrace();
            result.code = 500;
            result.setMsg("服务器错误");
        }
//        String re= JSON.toJSONString(stringObjectHashMap);
        return stringObjectHashMap;
    }
//    @ResponseBody
//    @GetMapping("/getgraph")
//    public Map<String, Object> getgraph(GraphQuery query) {
//        R<HashMap<String, Object>> result = new R<HashMap<String, Object>>();
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        try {
//           stringObjectHashMap = neo4jUtil.GetGraphNodeAndShip("MATCH (n:Movie)<-[r]-> (m)   RETURN  *  limit 15");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.code = 500;
//            result.setMsg("服务器错误");
//        }
////        System.out.println(JSON.toJSONString(stringObjectHashMap));
//        return stringObjectHashMap;
//    }
    @PostMapping("saveRelation")
    @ResponseBody
    public Msg saveRelation(Edges edges){
        String cypherSql = String.format("match(a),(b) where id(a)=%d and id(b)=%d create (a)-[r:%s]->(b)",edges.getSource(),edges.getTarget(),edges.getRelation());
        System.out.println(cypherSql);
        Msg msg = null;
        try {
            neo4jUtil.excuteCypherSql(cypherSql);
             msg = new Msg(200, "保存成功");
        }catch (Exception e){
             msg = new Msg(500, "保存失败");
        }
        return msg;
    }
    @PostMapping("deleteRelation")
    @ResponseBody
    public Msg deleteRelation(Edges edges){
        //MATCH (a:shkjgl)-[r:test]-(b:shkjgl) where id(a) = 2665 and id(b)=2667 delete r
        String cypherSql = String.format("match(a)-[r:%s]->(b) where id(a)=%d and id(b)=%d delete r",edges.getRelation(),edges.getSource(),edges.getTarget());
        System.out.println(cypherSql);
        Msg msg = null;
        try {
            neo4jUtil.excuteCypherSql(cypherSql);
             msg = new Msg(200, "删除成功");
        }catch (Exception e){
             msg = new Msg(500, "删除失败");
        }
        return msg;
    }
    @PostMapping("updateRelation")
    @ResponseBody
    public Msg updatelation(Edges edges){
        String cypherSql = String.format("match(a),(b) where id(a)=%d and id(b)=%d create (a)-[r:%s]->(b)",edges.getSource(),edges.getTarget(),edges.getRelation());
        System.out.println(cypherSql);
        Msg msg = null;
        try {
            neo4jUtil.excuteCypherSql(cypherSql);
             msg = new Msg(200, "保存成功");
        }catch (Exception e){
             msg = new Msg(500, "保存失败");
        }
        return msg;
    }
}
