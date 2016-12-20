package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.PropertyConfigurator;

import com.alibaba.fastjson.JSON;
import com.util.GameLog;
import com.util.IDWorker;

public abstract class BaseServer
{
	protected boolean terminate = true;
	protected ServerCfgInfo serverCfgInfo = null;
	public static IDWorker IDWORK = new IDWorker(1);

	public boolean start(String configPath)
	{
		if (!initComponent(initServerCfg(configPath), "加载服务器配置文件"))
		{
			return false;
		}

		PropertyConfigurator.configure(serverCfgInfo.logPath);
		return true;
	}

	public boolean initServerCfg(String filaPathName)
	{
		try (BufferedReader bufReader = Files.newBufferedReader(Paths.get(filaPathName)))
		{
			StringBuilder serverJson = new StringBuilder();
			String jsonStr = null;
			while ((jsonStr = bufReader.readLine()) != null)
			{
				serverJson.append(jsonStr);
			}

			serverCfgInfo = JSON.parseObject(serverJson.toString(), ServerCfgInfo.class);
		}
		catch (IOException e)
		{
			GameLog.info("加载游戏服务器配置出错" + e.getMessage());
			return false;
		}

		return true;
	}

	public boolean isTerminate()
	{
		return terminate;
	}

	public void setTerminate(boolean terminate)
	{
		this.terminate = terminate;
	}

	/**
	 * 初始化相关模块
	 */
	public boolean initComponent(boolean initResult, String componentName)
	{
		if (!initResult)
		{
			GameLog.error(componentName + "错误");
		}
		else
		{
			GameLog.info(componentName + "加载完成");
		}
		return initResult;
	}
}
