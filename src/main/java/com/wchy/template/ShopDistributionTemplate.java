package com.wchy.template;


import com.wchy.common.task.TaskContainer;
import com.wchy.common.util.Configuration;
import com.wchy.common.util.Count;
import com.wchy.common.util.DateUtil;

public class ShopDistributionTemplate {
	

	
	public static void main(String []args) {
		pushAllMQVshopDistributionItems_V2();
	}


	public static String pushAllMQVshopDistributionItems_V2(){
		String minMaxSql = "select min(id), max(id) from vshop_distribution_item b left join vshop_info a on a.vshop_id=b.vshop_id where b.`status`=1 and b.is_delete=0 and b.new_item_id is not null ";
		String querySql_templete="select b.new_item_id,b.vshop_id,b.id,a.new_user_id,b.pshop_id,b.status from vshop_distribution_item b left join vshop_info a on a.vshop_id=b.vshop_id where b.`status`=1 and b.is_delete=0 and b.new_item_id is not null ";
		
		Configuration<String, Object> context = new Configuration<String, Object>();
		context.put("fetch_size", 300);
		context.put("timeout",  5000);
		context.put("minMaxSql", minMaxSql);
		context.put("splitPkName", "id");
		context.put("threadNumber", 15);
		context.put("reader-type", "mysql");
		context.put("writer-type", "mysql");
		context.put("querySql_templete", querySql_templete);
		
		String result="";
        try {
        	long cTime = System.currentTimeMillis();
        	TaskContainer taskContainer = new TaskContainer();
        	taskContainer.doStart(context);
			
        	/*log.info("pushAllMQVshopDistributionItems-currentDate:" + DateUtil.getCurTime()
        	        + "推动MQ结束，用时: " + (System.currentTimeMillis() - cTime) + ""
        	        + "MQ总数据量: " + Count.atomicinteger.get() + "");*/
			//计数清零
			Count.atomicinteger.set(0);
		} catch (Exception e) {
			//log.error("查询分销商品，然后推送MQ出现异常，异常信息是：{}", e);
		}
		return result;
	}
	

}
