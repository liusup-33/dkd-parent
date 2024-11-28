package com.dkd.manage.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.SecurityUtils;
import com.dkd.manage.domain.TaskDetails;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.domain.request.TaskDetailRequest;
import com.dkd.manage.domain.request.TaskRequest;
import com.dkd.manage.mapper.EmpMapper;
import com.dkd.manage.mapper.TaskDetailsMapper;
import com.dkd.manage.mapper.VendingMachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.TaskMapper;
import com.dkd.manage.domain.Task;
import com.dkd.manage.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工单Service业务层处理
 * 
 * @author liusuping
 * @date 2024-11-28
 */
@Service
public class TaskServiceImpl implements ITaskService 
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    @Autowired
    private TaskDetailsMapper taskDetailsMapper;

    /**
     * 查询工单
     * 
     * @param taskId 工单主键
     * @return 工单
     */
    @Override
    public Task selectTaskByTaskId(Long taskId)
    {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    /**
     * 查询工单列表
     * 
     * @param task 工单
     * @return 工单
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增工单
     * 
     * @param taskRequest 工单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTask(TaskRequest taskRequest)
    {

        //补全工单信息
        Task task = new Task();
        task.setTaskCode(getTaskCode());
        task.setTaskStatus(1L);
        task.setCreateType(taskRequest.getCreateType());
        task.setInnerCode(taskRequest.getInnerCode());
        task.setUserId(taskRequest.getUserId());
        task.setUserName(empMapper.selectEmpById(taskRequest.getUserId()).getUserName());
        VendingMachine vendingMachine = vendingMachineMapper.selectVendingMachineByInnerCode(taskRequest.getInnerCode());
        task.setRegionId(vendingMachine.getRegionId());
        task.setDesc(taskRequest.getDesc());
        task.setProductTypeId(taskRequest.getProductTypeId());
        task.setAssignorId(SecurityUtils.getUserId());
        task.setAddr(vendingMachine.getAddr());
        task.setCreateTime(DateUtils.getNowDate());
        //保存工单信息
        int result = taskMapper.insertTask(task);

        //保存工单详情信息 仅补货工单
        if (taskRequest.getProductTypeId() == 2 && !taskRequest.getDetails().isEmpty()) {
            List<TaskDetails>  addList = new ArrayList<>();
            for (TaskDetailRequest detail : taskRequest.getDetails()) {
                TaskDetails taskDetails = new TaskDetails();
                taskDetails.setTaskId(task.getTaskId());
                taskDetails.setChannelCode(detail.getChannelCode());
                taskDetails.setExpectCapacity(detail.getExpectCapacity());
                taskDetails.setSkuId(detail.getSkuId());
                taskDetails.setSkuName(detail.getSkuName());
                taskDetails.setSkuImage(detail.getSkuImage());
                addList.add(taskDetails);
            }
            taskDetailsMapper.batchInsertTaskDetails(addList);
        }
        return result;
    }

    /**
     * 获取工单编号
     */
    public String getTaskCode()
    {
        String dateStr = DateUtils.getDate().replace("-", "");
        String key = "dkd.task.code" + dateStr;
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, 1, Duration.ofDays(1));
            return dateStr + "0001";
        }
        String code = redisTemplate.opsForValue().increment(key).toString();
        return dateStr + StrUtil.padPre(code, 4, "0");
    }

    /**
     * 修改工单
     * 
     * @param task 工单
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除工单
     * 
     * @param taskIds 需要删除的工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskIds(Long[] taskIds)
    {
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }

    /**
     * 删除工单信息
     * 
     * @param taskId 工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskId(Long taskId)
    {
        return taskMapper.deleteTaskByTaskId(taskId);
    }


    @Override
    public int cancelTask(Task task) {
        task.setTaskStatus(3L);
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }
}
