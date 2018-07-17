package managedbeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The SQL class provides a single method getSQL() for getting
 * the contents of .sql files in this package as strings.
 */
public class SQL {

  private static SQL sqlSingleton;

  private Map<String, String> statements;

  private SQL() {
    statements = new HashMap<String, String>();
  }

  /**
   *  Resources.getSQL(nm) returns a String containing the SQL statement
   *  named nm in the sql package.
   *  The string may contain '?' characters for parameterized statements.
   *
   *  @throws IOException
   */
  private synchronized String doGetSQL(String nm)
      throws IOException {
    if (statements.containsKey(nm)) {
      return statements.get(nm);
    }
    InputStream stream = SQL.class.getResourceAsStream(nm + ".sql");
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    StringBuilder builder = new StringBuilder();
    String line = reader.readLine();
    while (line != null) {
      builder.append(line);
      builder.append("\n");
      line = reader.readLine();
    }
    String statement = builder.toString();
    statements.put(nm, statement);
    return statement;
  }

  /**
   *  SQL.getSQL(nm) returns a String containing the SQL statement
   *  named nm in the sql package.
   *  The string may contain '?' characters for parametrized statements.
   *
   *  @throws IOException
   */
  public static String getSQL(String nm)
      throws IOException {
    if (sqlSingleton == null) {
      sqlSingleton = new SQL();
    }
    return sqlSingleton.doGetSQL(nm);
  }
}