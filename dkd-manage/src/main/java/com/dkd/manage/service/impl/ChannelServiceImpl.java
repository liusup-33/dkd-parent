package com.dkd.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.utils.DateUtils;
import com.dkd.manage.domain.request.ChannelConfigRequest;
import com.dkd.manage.domain.request.ChannelListRequest;
import com.dkd.manage.domain.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.ChannelMapper;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.service.IChannelService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 售货机货道Service业务层处理
 * 
 * @author liusuping
 * @date 2024-11-17
 */
@Service
public class ChannelServiceImpl implements IChannelService 
{
    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 查询售货机货道
     * 
     * @param id 售货机货道主键
     * @return 售货机货道
     */
    @Override
    public Channel selectChannelById(Long id)
    {
        return channelMapper.selectChannelById(id);
    }

    /**
     * 查询售货机货道列表
     * 
     * @param channel 售货机货道
     * @return 售货机货道
     */
    @Override
    public List<Channel> selectChannelList(Channel channel)
    {
        return channelMapper.selectChannelList(channel);
    }

    /**
     * 新增售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    @Override
    public int insertChannel(Channel channel)
    {
        channel.setCreateTime(DateUtils.getNowDate());
        return channelMapper.insertChannel(channel);
    }

    /**
     * 修改售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    @Override
    public int updateChannel(Channel channel)
    {
        channel.setUpdateTime(DateUtils.getNowDate());
        return channelMapper.updateChannel(channel);
    }

    /**
     * 批量删除售货机货道
     * 
     * @param ids 需要删除的售货机货道主键
     * @return 结果
     */
    @Override
    public int deleteChannelByIds(Long[] ids)
    {
        return channelMapper.deleteChannelByIds(ids);
    }

    /**
     * 删除售货机货道信息
     * 
     * @param id 售货机货道主键
     * @return 结果
     */
    @Override
    public int deleteChannelById(Long id)
    {
        return channelMapper.deleteChannelById(id);
    }

    @Override
    public List<ChannelVO> selectChannelByInnerCode(String innerCode) {
        return channelMapper.selectChannelByInnerCode(innerCode);
    }

    @Transactional
    @Override
    public int updateChannelConfig(ChannelConfigRequest channelConfigRequest) {
        List<Channel> addList = new ArrayList<>();
        ChannelListRequest[] channelList = channelConfigRequest.getChannelList();
        for (ChannelListRequest channelListRequest : channelList) {
            //通过设备编码、货道编码查询售货机货道
            Channel channel = channelMapper.selectChannelByChannelCode(channelConfigRequest.getInnerCode(),channelListRequest.getChannelCode());
            if(channel != null){
                channel.setSkuId(channelListRequest.getSkuId());
                channel.setCreateTime(DateUtils.getNowDate());
                channel.setUpdateTime(DateUtils.getNowDate());
                addList.add(channel);
            }
        }
        //通过设备编码删除售货机货道
        int i = channelMapper.deleteChannelByInnerCode(channelConfigRequest.getInnerCode());
        if (i < 0) {
            throw new RuntimeException("删除售货机货道失败");
        }
        //批量新增售货机货道
        return channelMapper.batchInsertChannel(addList);
    }
}
