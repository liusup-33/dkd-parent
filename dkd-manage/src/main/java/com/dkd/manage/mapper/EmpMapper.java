package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Emp;

/**
 * 工单员工Mapper接口
 * 
 * @author liusuping
 * @date 2024-11-21
 */
public interface EmpMapper 
{
    /**
     * 查询工单员工
     * 
     * @param id 工单员工主键
     * @return 工单员工
     */
    public Emp selectEmpById(Long id);

    /**
     * 查询工单员工列表
     * 
     * @param emp 工单员工
     * @return 工单员工集合
     */
    public List<Emp> selectEmpList(Emp emp);

    /**
     * 新增工单员工
     * 
     * @param emp 工单员工
     * @return 结果
     */
    public int insertEmp(Emp emp);

    /**
     * 修改工单员工
     * 
     * @param emp 工单员工
     * @return 结果
     */
    public int updateEmp(Emp emp);

    /**
     * 删除工单员工
     * 
     * @param id 工单员工主键
     * @return 结果
     */
    public int deleteEmpById(Long id);

    /**
     * 批量删除工单员工
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmpByIds(Long[] ids);
}
