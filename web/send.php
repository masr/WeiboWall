<?php

    $_SC=array();
    include_once('config.php');
	include_once('class_mysql.php');

	
		$db= new dbstuff;
		$db->charset = $_SC['dbcharset'];
		$db->connect($_SC['dbhost'], $_SC['dbuser'], $_SC['dbpw'], $_SC['dbname'], $_SC['pconnect']);
	   $db->query("set names utf8");
	   $time=time();
	   $phone=$_GET['telephone'];
	   $user=$_GET['user'];
	   $content=$_GET['content'];
	   if (empty($content)||empty($phone))
	   exit();
	   
	   if (empty($user))
	   $user="某粉丝";
	   
	   $user=str_replace("\n","",$user);
	   $content=str_replace("\r","",$content);
	   $content=str_replace("\n","",$content);
	   $user=str_replace("\r","",$user);
	   $db->query("insert into weibo (date,telephone,user,content) values($time,'$phone','$user','$content')")
?>