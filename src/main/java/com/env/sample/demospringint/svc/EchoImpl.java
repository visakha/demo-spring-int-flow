package com.env.sample.demospringint.svc;

public class EchoImpl implements EchoService {

	@Override
	public String echo(String msg) {
		System.out.println("See me here");
		return "Impl - " + msg;
	}

}
