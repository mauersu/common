//
//  SecurityUtil.h
//  Smile
//
//  Created by 蒲晓涛 on 12-11-24.
//  Copyright (c) 2012年 BOX. All rights reserved.
//

// 版权属于原作者
// http://code4app.com (cn) http://code4app.net (en)
// 发布代码于最专业的源码分享网站: Code4App.com

#import "SecurityUtil.h"
//#import "GTMBase64.h"
#import "Base64.h"
#import "NSData+AES.h"


@implementation SecurityUtil

#pragma mark - base64

+ (NSString*)encodeBase64String:(NSString * )input {
    
    /*
    NSData *data = [input dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES]; 
    data = [GTMBase64 encodeData:data];
    NSString *base64String = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	return base64String;
     */
    NSData *data = [input dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES];
    NSString * str = [Base64 encode:data];
    
    return str;
    
}

+ (NSString*)decodeBase64String:(NSString * )input {
    /*
    NSData *data = [input dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES]; 
    data = [GTMBase64 decodeData:data]; 
    NSString *base64String = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	return base64String;
     */
    NSData *data = [input dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES];

    NSString * str = [Base64 decodeString:[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]];
    return str;
}

+ (NSString*)encodeBase64Data:(NSData *)data {
    /*
	data = [GTMBase64 encodeData:data]; 
    NSString *base64String = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	return base64String;
     */
    
    NSString * str = [Base64 encode:data];
    
    return str;
}

+ (NSString*)decodeBase64Data:(NSData *)data {
    /*
	data = [GTMBase64 decodeData:data]; 
    NSString *base64String = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	return base64String;
     */
    
    NSString * str = [Base64 decodeString:[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]];
    return str;
}


#pragma mark - AES加密
//将string转成带密码的data
+(NSString*)encryptAESData:(NSString*)string app_key:(NSString*)key
{
    //将nsstring转化为nsdata
    NSData *data = [string dataUsingEncoding:NSUTF8StringEncoding];
    //使用密码对nsdata进行加密
    NSData *encryptedData = [data AES128EncryptWithKey:key];
    NSLog(@"加密后的字符串 :%@",[encryptedData base64Encoding]);
    
    return [encryptedData base64Encoding];
}

#pragma mark - AES解密
//将带密码的data转成string
+(NSString*)decryptAESData:(NSData*)data  app_key:(NSString*)key
{
    //使用密码对data进行解密
    NSData *decryData = [data AES128DecryptWithKey:key];
    //将解了密码的nsdata转化为nsstring
    NSString *str = [[NSString alloc] initWithData:decryData encoding:NSUTF8StringEncoding];
//    NSString * str1 = [[NSString alloc] initWithData:[GTMBase64 encodeData:decryData] encoding:NSASCIIStringEncoding];
//    
//    NSString * str2 = [[NSString alloc] initWithData:[GTMBase64 encodeData:decryData] encoding:NSUnicodeStringEncoding];
//    NSString * str3 = [SecurityUtil decodeBase64Data:decryData];
//    NSString * str4 = [SecurityUtil encodeBase64Data:decryData];
    
    return str;
}

@end
