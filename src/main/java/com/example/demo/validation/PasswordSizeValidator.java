package com.example.demo.validation;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.example.demo.annotation.ValidPasswordSize;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<ValidPasswordSize, String>{

	@Value("${password.max}")
	private int maxLength;
	
	@Value("${password.min}")
	private int minLength;
	
	
	@Override
	public void initialize(ValidPasswordSize constraintAnnotation) {
		
		// アノテーションのdefault設定値（動的でない）
		System.out.println("annotation max : " + constraintAnnotation.max());
		System.out.println("annotation min : " + constraintAnnotation.min());
		
		// application.ymlの設定からフィールドにInjectした値
		System.out.println("environment max : " + this.maxLength);
		System.out.println("environment min : " + this.minLength);
		
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		// メッセージを動的なlengthの上限、下限で再構築する
		String messageTemplate = "{0}は{1}文字以上{2}文字以内で入力してください";
		String message = MessageFormat.format(messageTemplate, new Object[] {"パスワード", this.minLength, this.maxLength});
	
		
		if(!StringUtils.hasLength(value)) {
			// バリデーションエラーの場合は既定のテンプレートでのメッセージ構築を無効化して、メッセージ付きでConstraitViolationを追加
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		
		if (value.length() < this.minLength || value.length() > this.maxLength) {
			// バリデーションエラー場合は既定のテンプレートでのメッセージ構築を無効化して、メッセージ付きでConstraitViolationを追加
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
		
		return true;
		
	}

}
