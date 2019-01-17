package org.gnuhpc.bigdata.designpattern.builder;

// Builder 模式主要是为了构造函数非常多的参数，有可选的有必选的时非常有用的一种模式。
// 如下是第一种写法，实体类（LunchOrder）有一个构造函数传入的是这个类的builder类（inner static class）
// builder类有所有这个实体类的成员变量。build方法直接将自己传入这个实体类的构造函数。

import lombok.Getter;

@Getter
public class LunchOrder {

	public static class Builder {
		private String bread;
		private String condiments;
		private String dressing;
		private String meat;
		
		public Builder() {
			
		}

		// 要点： build调用原函数的构造函数，将this转入
		public LunchOrder build() {
			return new LunchOrder(this);
		}
		
		public Builder bread(String bread) {
			this.bread = bread;
			return this;
		}
		
		public Builder condiments(String condiments) {
			this.condiments = condiments;
			return this;
		}
		
		public Builder dressing(String dressing) {
			this.dressing = dressing;
			return this;
		}
		
		public Builder meat(String meat) {
			this.meat = meat;
			return this;
		} 
		
	}
	
	private final String bread;
	private final String condiments;
	private final String dressing;
	private final String meat;

	//要点，原类构造函数要private， 唯一参数为builder
	private LunchOrder(Builder builder) {
		this.bread = builder.bread;
		this.condiments = builder.condiments;
		this.dressing = builder.dressing;
		this.meat = builder.meat;
	}

}
