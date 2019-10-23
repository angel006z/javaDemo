package com.meida.generatebackend.config.rules;

public enum QuerySQL {
	SQLSERVER("sqlserver", "SELECT NAME FROM SysObjects Where XType='U'",
			"SELECT (case when a.colorder = 1 then cast(d.name as varchar(50)) else '' end) NAME, (case when a.colorder = 1 then isnull(cast(f.value as varchar(50)), '') "
					+ " else '' end) COMMENT FROM syscolumns a inner join sysobjects d on a.id = d.id and d.xtype = 'U' and d.name <> 'sys.extended_properties'"
					+ " left join sys.extended_properties f on a.id = f.major_id and f.minor_id = 0 Where (case when a.colorder = 1 then d.name else '' end) <>''",

			"SELECT a.name FIELD,(case when COLUMNPROPERTY(a.id,a.name,'IsIdentity')=1 then '1'else '0' end) isAuto, "
					+ "(case when (SELECT count(*) FROM sysobjects WHERE (name in (SELECT name FROM sysindexes WHERE (id = a.id) AND (indid in "
					+ "(SELECT indid FROM sysindexkeys WHERE (id = a.id) AND (colid in (SELECT colid FROM syscolumns WHERE (id = a.id) AND (name = a.name))))))) "
					+ "AND (xtype = 'PK'))>0 then 'PRI' else '' end) vKEY,b.name TYPE,(case when a.isnullable=1 then 'yes'else '' end) 允许空,isnull(cast(g.[value] as varchar(50)), ' ') AS COMMENT"
					+ " FROM  syscolumns a left join systypes b on a.xtype=b.xusertype inner join sysobjects d on a.id=d.id and d.xtype='U' and d.name<>'dtproperties' "
					+ "left join syscomments e on a.cdefault=e.id left join sys.extended_properties g on a.id=g.major_id AND a.colid=g.minor_id left join sys.extended_properties f on "
					+ "d.id=f.class and f.minor_id=0 where b.name is not null AND d.name='%s' order by a.id,a.colorder",
			"NAME", "COMMENT", "FIELD", "TYPE", "COMMENT", "vKEY", "isAuto"),

	MYSQL("mysql", "show tables", "show table status", "show full fields from %s", "NAME", "COMMENT", "FIELD", "TYPE",
			"COMMENT", "KEY", "isAuto"), // mysql主键是否自动增长暂未修改

	ORACLE("oracle", "SELECT * FROM USER_TABLES", "SELECT * FROM USER_TAB_COMMENTS",
			"SELECT A.COLUMN_NAME, CASE WHEN A.DATA_TYPE='NUMBER' THEN "
					+ "(CASE WHEN A.DATA_PRECISION IS NULL THEN A.DATA_TYPE "
					+ "WHEN NVL(A.DATA_SCALE, 0) > 0 THEN A.DATA_TYPE||'('||A.DATA_PRECISION||','||A.DATA_SCALE||')' "
					+ "ELSE A.DATA_TYPE||'('||A.DATA_PRECISION||')' END) "
					+ "ELSE A.DATA_TYPE END DATA_TYPE, B.COMMENTS,DECODE(C.POSITION, '1', 'PRI') KEY "
					+ "FROM USER_TAB_COLUMNS A INNER JOIN USER_COL_COMMENTS B ON A.TABLE_NAME = B.TABLE_NAME"
					+ " AND A.COLUMN_NAME = B.COLUMN_NAME LEFT JOIN USER_CONSTRAINTS D "
					+ "ON D.TABLE_NAME = A.TABLE_NAME AND D.CONSTRAINT_TYPE = 'P' "
					+ "LEFT JOIN USER_CONS_COLUMNS C ON C.CONSTRAINT_NAME = D.CONSTRAINT_NAME "
					+ "AND C.COLUMN_NAME=A.COLUMN_NAME WHERE A.TABLE_NAME = '%s' ",
			"TABLE_NAME", "COMMENTS", "COLUMN_NAME", "DATA_TYPE", "COMMENTS", "KEY", "isAuto");// oracle主键是否自动增长暂未修改

	private final String dbType;
	private final String tablesSql;
	private final String tableCommentsSql;
	private final String tableFieldsSql;
	private final String tableName;
	private final String tableComment;
	private final String fieldName;
	private final String fieldType;
	private final String fieldComment;
	private final String fieldKey;
	private final String isAuto;

	QuerySQL(final String dbType, final String tablesSql, final String tableCommentsSql, final String tableFieldsSql,
			final String tableName, final String tableComment, final String fieldName, final String fieldType,
			final String fieldComment, final String fieldKey, final String isAuto) {
		this.dbType = dbType;
		this.tablesSql = tablesSql;
		this.tableCommentsSql = tableCommentsSql;
		this.tableFieldsSql = tableFieldsSql;
		this.tableName = tableName;
		this.tableComment = tableComment;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldComment = fieldComment;
		this.fieldKey = fieldKey;
		this.isAuto = isAuto;
	}

	public String getDbType() {
		return dbType;
	}

	public String getTablesSql() {
		return tablesSql;
	}

	public String getTableCommentsSql() {
		return tableCommentsSql;
	}

	public String getTableFieldsSql() {
		return tableFieldsSql;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public String getFieldComment() {
		return fieldComment;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public String getIsAuto() {
		return isAuto;
	}

}
