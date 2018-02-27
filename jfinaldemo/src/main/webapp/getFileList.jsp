<%@page import="java.io.File,java.util.ArrayList,java.util.List" contentType="text/html" pageEncoding="UTF-8"%>
        <%
            response.setCharacterEncoding("utf-8");
            String CONTENT_TYPE = "text/html; charset=utf-8";
            response.setContentType(CONTENT_TYPE);
            
            String baseDirPath = "/var/ftp";    //用于替换文件目录，获取url路径
            File dir = new File("/var/ftp/teacher/music");
            
            if (!dir.exists() && !dir.isDirectory()) {
                String resultJson = "[]";
                out.println(resultJson);
                return;
            }
            
            List<File> dirs = new ArrayList();
            dirs.add(dir);
            //找到所有的子目录
            for (int i = 0; i < dirs.size(); i++) {
                File dirItem = dirs.get(i);
                for (File file : dirItem.listFiles()) {
                    if (file.isDirectory()) {
                        dirs.add(file);
                    }
                }
            }
            
            out.println("[");
            
            int n = 0;
            for (File dirItem : dirs) {
                File[] fileItems = dirItem.listFiles();
                for (File file : fileItems) {
                    
                    if (file.isDirectory()) {
                        continue;
                    }
                    
                    String fileName = file.getName();
                    
                    if (!fileName.endsWith(".mp3")) {
                        continue;
                    }
                    
                    String absolutePath = file.getAbsolutePath();
                    String url = absolutePath.replace(baseDirPath, "");
                    
                    if (n > 0) {
                        out.println(",");
                    }
                    out.println("{"
                            + "\"artist\": \"unknow\","
                            + "\"avatarURL\": \"avatar/cqz.jpg\","
                            + "\"musicAlbum\": \"" + fileName + "\","
                            + "\"musicName\": \"" + fileName.replace(".mp3", "") + "\","
                            + "\"musicTime\": \"245\","
                            + "\"musicURL\": \"" + url + "\""
                            + "}");
                    n++;
                }
            }
            
            out.print("]");
            out.flush();
            
        %>