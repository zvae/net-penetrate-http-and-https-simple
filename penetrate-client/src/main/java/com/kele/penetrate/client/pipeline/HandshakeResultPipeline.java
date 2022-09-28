package com.kele.penetrate.client.pipeline;

import com.alibaba.fastjson.JSONObject;
import com.kele.penetrate.factory.annotation.Autowired;
import com.kele.penetrate.factory.annotation.Recognizer;
import com.kele.penetrate.factory.annotation.Register;
import com.kele.penetrate.page.ClientLogPageManager;
import com.kele.penetrate.page.MainFrame;
import com.kele.penetrate.protocol.HandshakeResult;
import com.kele.penetrate.utils.FileUtils;
import com.kele.penetrate.utils.Func;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
@Register
@Recognizer
public class HandshakeResultPipeline implements Func<Object, Boolean> {
    @Autowired
    private ClientLogPageManager clientLogPageManager;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private FileUtils fileUtils;

    @Override
    public Boolean func(Object msg) {
        if (msg instanceof HandshakeResult) {
            HandshakeResult handshakeResult = (HandshakeResult) msg;
            if (handshakeResult.isSuccess()) {
                clientLogPageManager.addLog("启动成功 :");
                clientLogPageManager.addLog(handshakeResult.getFailMessages());
                mainFrame.getRunButton().setText("暂停");
                mainFrame.getRunButton().setEnabled(true);

                JSONObject recordOperationJson = new JSONObject();
                recordOperationJson.put("customDomainName", mainFrame.getCustomDomainName());
                recordOperationJson.put("isAutoStart", mainFrame.isAutoStart());
                recordOperationJson.put("ip", mainFrame.getIp());
                recordOperationJson.put("port", mainFrame.getPort());
                fileUtils.writeLocalStr(recordOperationJson.toJSONString(), fileUtils.rootDirectory, fileUtils.recordOperation);
            } else {
                clientLogPageManager.addLog("启动失败 :");
                mainFrame.getRunButton().setText("启动");
                clientLogPageManager.addLog(handshakeResult.getFailMessages());
                mainFrame.setAllEditable(true);
            }
            return true;
        }
        return false;
    }
}
