package com.anshul.beans;
import java.io.*;
@SuppressWarnings("serial")
public class TransBean implements Serializable{
	private String transId,uName,fromPort,toPort,time;
	private long seatNo,cruiseNo;
	public void setUName(String uName)
	{
		this.uName = uName;
	}
	public String getUName()
	{
		return uName;
	}
	public void setTranId(String  transId)
	{
		this.transId = transId;
	}
	public void setCruiseNo(long cruiseNo)
	{
		this.cruiseNo = cruiseNo;
	}
	public void setFromPort(String  fromPort)
	{
		this.fromPort = fromPort;
	}
	public void setToPort(String toPort)
	{
		this.toPort = toPort;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setSeats(long seatNo)
	{
		this.seatNo = seatNo;
	}

	public String getTranId()
	{
		return transId  ;
	}
	public long getCruiseNo()
	{
		return cruiseNo ;
	}
	public String getFromPort()
	{
		return fromPort  ;
	}
	public String getToPort()
	{
		return toPort  ;
	}
	public String getTime()
	{
		return time  ;
	}
	public long getSeats()
	{
		return seatNo ;
	}

}
