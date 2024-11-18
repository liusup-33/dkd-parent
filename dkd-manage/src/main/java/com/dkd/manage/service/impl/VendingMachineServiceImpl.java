package com.dkd.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.uuid.UUIDUtils;
import com.dkd.manage.domain.*;
import com.dkd.manage.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.service.IVendingMachineService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备管理Service业务层处理
 * 
 * @author liusuping
 * @date 2024-11-17
 */
@Service
public class VendingMachineServiceImpl implements IVendingMachineService 
{
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    @Autowired
    private VmTypeMapper vmMapper;

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public VendingMachine selectVendingMachineById(Long id)
    {
        return vendingMachineMapper.selectVendingMachineById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param vendingMachine 设备管理
     * @return 设备管理
     */
    @Override
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine)
    {
        return vendingMachineMapper.selectVendingMachineList(vendingMachine);
    }

    /**
     * 新增设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {
        String innerCode = UUIDUtils.getUUID();
        vendingMachine.setInnerCode(innerCode);
        vendingMachine.setCreateTime(DateUtils.getNowDate());

        //设备容量
        VmType vmType = vmMapper.selectVmTypeById(vendingMachine.getVmTypeId());
        vendingMachine.setChannelMaxCapacity(vmType.getChannelMaxCapacity());

        //区域、点位、合作商等信息
        Node node = nodeMapper.selectNodeById(vendingMachine.getNodeId());
        vendingMachine.setAddr(node.getDetailedAddress());
        vendingMachine.setPartnerId(node.getPartnerId());
        vendingMachine.setBusinessType(node.getBusinessType());
        vendingMachine.setRegionId(node.getRegionId());
        vendingMachine.setVmStatus(0L);
        vendingMachine.setCreateTime(DateUtils.getNowDate());// 创建时间
        vendingMachine.setUpdateTime(DateUtils.getNowDate());// 更新时间

        int result = vendingMachineMapper.insertVendingMachine(vendingMachine);

        List<Channel> channelList = new ArrayList<>();
        for (int i = 1; i <= vmType.getVmRow(); i++) { // 外层行
            for (int j = 1; j <= vmType.getVmCol(); j++) {// 内层列
                Channel channel = new Channel();
                channel.setChannelCode(i + "-" + j);// 货道编号
                channel.setVmId(vendingMachine.getId());// 售货机id
                channel.setInnerCode(vendingMachine.getInnerCode());// 售货机编号
                channel.setMaxCapacity(vmType.getChannelMaxCapacity());// 货道最大容量
                channel.setCreateTime(DateUtils.getNowDate());// 创建时间
                channel.setUpdateTime(DateUtils.getNowDate());// 更新时间
                channelList.add(channel);
            }
        }
        channelMapper.batchInsertChannel(channelList);
        return result;
    }

    /**
     * 修改设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Override
    public int updateVendingMachine(VendingMachine vendingMachine)
    {
        vendingMachine.setUpdateTime(DateUtils.getNowDate());
        return vendingMachineMapper.updateVendingMachine(vendingMachine);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineByIds(Long[] ids)
    {
        return vendingMachineMapper.deleteVendingMachineByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineById(Long id)
    {
        return vendingMachineMapper.deleteVendingMachineById(id);
    }
}
