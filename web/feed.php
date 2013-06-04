
<?php
$_SC=array();
include_once('config.php');
include_once('class_mysql.php');


$db= new dbstuff;
$db->charset = $_SC['dbcharset'];
$db->connect($_SC['dbhost'], $_SC['dbuser'], $_SC['dbpw'], $_SC['dbname'], $_SC['pconnect']);
$db->query("set names utf8");
$id=$_POST['latestId'];

	$resource=$db->query("select * from weibo where weibo_id>$id order by weibo_id asc limit 23");
	$results = $db->getDoubleArray($resource);

$str="[";

$i=1;
foreach($results as $v)
{
	$str.="{phone:'$v[telephone]',user:'$v[user]',content:'$v[content]',weibo_id:$v[weibo_id]},";
	$i++;
}

if ($i!=1){
$len=strlen($str);
$str=substr($str,0,$len-1);
}
$str.="]";

echo $str;
?>