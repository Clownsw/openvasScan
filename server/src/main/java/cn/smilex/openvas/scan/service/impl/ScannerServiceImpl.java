package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.ScannerService;
import org.springframework.stereotype.Service;

/**
 * @author smilex
 */
@Service
public class ScannerServiceImpl implements ScannerService {

    /**
     * 查询所有扫描器
     *
     * @return 结果
     */
    @Override
    public String selectAllScanner() {
        OpenvasCommandParse<Object> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_SCANNERS);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml());
    }
}
