package indi.wrenn.studentsystem.util;
//生成sql语句的工具类
public class CreateSql {

	//根据查询内容、选项从学生表里返回特定的sql语句
	public static String getStudent_Sql(String str,String option){
		String sql = null;
		if("全部".equals(option)){
			sql = "select * from tb_Student" ;
		}else if("学号".equals(option)){
			sql = "select * from tb_Student where Student_Id like '%"+str+"%'";
		}else if("姓名".equals(option)){
			sql = "select * from tb_Student where Student_Name like '%"+str+"%'";
		}else if("性别".equals(option)){
			sql = "select * from tb_Student where Student_Sex like '%"+str+"%'";
		}else if("年级".equals(option)){
			sql = "select * from tb_Student where Grade like '%"+str+"%'";
		}else if("班级".equals(option)){
			sql = "select * from tb_Student where Classe like '%"+str+"%'";
		}else if("专业".equals(option)){
			sql = "select * from tb_Student where Major_Name  like '%"+str+"%'";
		}else if("院系".equals(option)){
			sql = "select * from tb_Student where Department_Name like '%"+str+"%'";
		}
		return sql;
	}
	
	//多条件查询的sql语句创建
	public static String getConditions_Sql(String id,String name,String sex,String grade,String department,String major,String classe){
		StringBuilder sql = new StringBuilder("select * from tb_Student where 1=1");
		if(!id.equals("")){
			sql.append(" and Student_Id like '%" + id + "%'  ");
		}
		if(!name.equals("")){
			sql.append(" and Student_Name like '%" + name + "%'  ");
		}
		if(!sex.equals("")){
			sql.append(" and Student_Sex like '%" + sex + "%'  ");
		}
		if(!grade.equals("")){
			sql.append(" and Grade like '%" + grade + "%'  ");
		}
		if(!department.equals("")){
			sql.append(" and Department_Name like '%" + department + "%'  ");
		}
		if(!major.equals("")){
			sql.append(" and Major_Name like '%" + major + "%'  ");
		}
		if(!classe.equals("")){
			sql.append(" and Classe like '%" + classe + "%'  ");
		}
		
		return sql.toString();
	}
	
	//根据查询内容、选项从学生表里返回特定的sql语句
		public static String getStudent_Sql(String grade,String major,String str,String option){
			String sql = null;
			if("全部".equals(option)){
				sql = "select * from tb_Student where Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}else if("学号".equals(option)){
				sql = "select * from tb_Student where Student_Id like '%"+str+"%' and Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}else if("姓名".equals(option)){
				sql = "select * from tb_Student where Student_Name like '%"+str+"%' and Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}else if("性别".equals(option)){
				sql = "select * from tb_Student where Student_Sex like '%"+str+"%' and Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}else if("年级".equals(option)){
				sql = "select * from tb_Student where Grade like '%"+str+"%' and Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}else if("班级".equals(option)){
				sql = "select * from tb_Student where Classe like '%"+str+"%' and Grade='"+grade+"' and Major_Name='"+major+"'" ;
			}
			return sql;
		}
	
	
	
	
}
