package com.kele.penetrate.client;


import com.kele.penetrate.Start;
import com.kele.penetrate.config.Config;
import com.kele.penetrate.factory.annotation.Autowired;
import com.kele.penetrate.factory.annotation.Recognizer;
import com.kele.penetrate.page.ClientLogPageManager;
import com.kele.penetrate.page.MainFrame;
import com.kele.penetrate.utils.UUIDUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Recognizer
@ChannelHandler.Sharable
@SuppressWarnings("unused")
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    //<editor-fold desc="注入">
    @Autowired
    private ConnectHandler connectHandler;
    @Autowired
    private Config config;
    @Autowired
    private UUIDUtils uuidUtils;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private ClientLogPageManager clientLogPageManager;
    private boolean isFirst = true;
    //</editor-fold>

    //<editor-fold desc="接收通道消息">
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Start.clientEvents.notice(msg);
    }
    //</editor-fold>

    //<editor-fold desc="通道连接激活">
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        connectHandler.setChannel(ctx.channel());
        if (isFirst && mainFrame.isAutoStart()) {
            isFirst = false;
            mainFrame.startButtonClickHandle(null);
        } else {
            if (connectHandler.getHandshake() != null) {
                connectHandler.send(connectHandler.getHandshake());
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="通道断开">
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        connectHandler.disconnect();
    }
    //</editor-fold>

    //<editor-fold desc="长时间没有通信">
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        ctx.flush();
        ctx.close();
    }
    //</editor-fold>
}
