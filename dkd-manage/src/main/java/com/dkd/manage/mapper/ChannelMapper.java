package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.vo.ChannelVO;
import org.apache.ibatis.annotations.Param;

/**
 * 售货机货道Mapper接口
 * 
 * @author liusuping
 * @date 2024-11-17
 */
public interface ChannelMapper 
{
    /**
     * 查询售货机货道
     * 
     * @param id 售货机货道主键
     * @return 售货机货道
     */
    public Channel selectChannelById(Long id);

    /**
     * 查询售货机货道列表
     * 
     * @param channel 售货机货道
     * @return 售货机货道集合
     */
    public List<Channel> selectChannelList(Channel channel);

    /**
     * 新增售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int insertChannel(Channel channel);

    //批量插入
    public int batchInsertChannel(List<Channel> channelList);

    /**
     * 修改售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int updateChannel(Channel channel);

    /**
     * 删除售货机货道
     * 
     * @param id 售货机货道主键
     * @return 结果
     */
    public int deleteChannelById(Long id);

    /**
     * 批量删除售货机货道
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChannelByIds(Long[] ids);

    /**
     * 根据售商品ID查询货道
     * @param skuIds
     * @return
     */
    public List<Channel> selectChannelListBySkuIds(@Param("skuIds") Long[] skuIds);

    /**
     * 根据售货机内码查询货道
     * @param innerCode
     * @return
     */
    List<ChannelVO> selectChannelByInnerCode(@Param("innerCode") String innerCode);

    /**
     * 通过设备编码、货道编码查询售货机货道
     */
    Channel selectChannelByChannelCode(@Param("innerCode") String innerCode,@Param("channelCode") String channelCode);

    /**
     * 通过设备编码删除售货机货道
     * @param innerCode
     * @return
     */
    int deleteChannelByInnerCode(@Param("innerCode") String innerCode);
}
