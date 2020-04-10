package com.newtouch.structfund.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.structfund.entity.CostParameters;
import com.newtouch.structfund.entity.User;
import com.newtouch.structfund.mapper.CostMapper;

@Controller
@ResponseBody
@RequestMapping("costParameters")
@SuppressWarnings("all")
public class CostParametersAction {// 费用参数维护
	@Autowired
	CostMapper costMapper;

	@RequestMapping("costAdd")
	public Map costAdd(CostParameters costParameters, HttpSession session) {
		Map<String, String> result = new HashMap<String, String>();
		CostParameters costParameters2 = null;
		try {
			costParameters2 = costMapper.costSelectBy(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (costParameters2 != null) {
			result.put("result", "信息已存在");
			return result;
		}
		try {
			if (costParameters.getRegisterCode().equals("ZX")) {
				costParameters.setRegistration("新致");
			}
			User user = (User) session.getAttribute("userInfo");
			if (user != null) {
			costParameters.setFinalMaintainer(user.getUsername());
			}
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			costParameters.setFinalUpdateTime(simpleDateFormat.format(date));
			costMapper.costAdd(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.put("result", "保存成功");
		return result;

	}

	@RequestMapping("costSelect")
	public Map costSelect(CostParameters costParameters, Integer currentPage, Integer pageSize) {
		Map<String, Object>map=new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("startRow", (currentPage - 1) * pageSize);
		param.put("pageSize", pageSize);
		param.put("costParameters", costParameters);
		List<CostParameters> costList = null;
		Integer totalRows = 0;
		try {
			costList = costMapper.costSelect(param);
			totalRows = costMapper.count(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("dataList", costList);
		map.put("totalRows", totalRows);
		return map;

	}

	@RequestMapping("costUpdate")
	public Map costUpdate(CostParameters costParameters, HttpSession session) {
		Map map = new HashMap();
		CostParameters costParameters2 = null;
		try {
			costParameters2 = costMapper.costSelectBy(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (costParameters2 == null) {
			map.put("result", "信息不存在");
			return map;
		}
		int status = 0;
		try {
			User user = (User) session.getAttribute("userInfo");
			if (user != null) {
			costParameters.setFinalMaintainer(user.getUsername());
			}
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			costParameters.setFinalUpdateTime(simpleDateFormat.format(date));
			status = costMapper.costUpdate(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "修改异常");
			return map;
		}
		if (status > 0) {
			map.put("result", "修改成功");
			return map;
		}
		map.put("result", "修改失败");
		return map;

	}

	@RequestMapping("costDelete")
	public Map costDelete(@RequestBody CostParameters costParameters) {
		System.out.println(costParameters);
		Map map = new HashMap();
		CostParameters costParameters2 = null;
		try {
			costParameters2 = costMapper.costSelectBy(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (costParameters2 == null) {
			map.put("result", "信息不存在");
			return map;
		}
		try {
			costMapper.costDelete(costParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "删除异常");
			return map;
		}
		map.put("result", "删除成功");
		return map;

	}

	@RequestMapping("costDeleteAll")
	public Map costDeleteAll(@RequestBody List<CostParameters> costParametersList) {
		Map map = new HashMap();
		try {
			for (CostParameters costParameters2 : costParametersList) {
				costMapper.costDelete(costParameters2);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "删除异常");
			return map;
		}
		map.put("result", "删除成功");
		return map;

	}
}
