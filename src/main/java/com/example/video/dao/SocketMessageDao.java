package com.example.video.dao;

import com.example.video.websocket.pojo.SocketMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SocketMessageDao {

    @Select("select receive_id  as id from video_socket_message where launch_id = #{id} union select launch_id as id from video_socket_message where receive_id = #{id};")
    List<Long> listFriendId(Long id);

    /**
     * 找出与launchId以及ReceiveId相关联的所有数据
     * @param launchId
     * @param receiveId
     * @return
     */
    @Select("select a.id,a.launch_id,a.receive_id,a.content,a.create_time from (select * from video_socket_message where launch_id = #{launchId} and receive_id = #{receiveId} union all select * from video_socket_message where launch_id = #{receiveId}  and receive_id = #{launchId}) as a order by id asc")
    List<SocketMessage> listSocketMessageById(Long launchId,Long receiveId);

    @Insert("insert into video_socket_message(launch_id,receive_id,content,create_time) values (#{launchId},#{receiveId},#{content},#{createTime})")
    void saveSocketMessage(SocketMessage socketMessage);

}
