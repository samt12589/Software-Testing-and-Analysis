import os
import subprocess
import shutil
import webbrowser


print("Please Make sure you placed the script straight into the Randoop project folder.\n")



# Maven commands

clean_command = ['mvn', 'clean']
compile_command = ['mvn', 'compile']
java_command = ['java' ,'-classpath' ,'./target/classes/:./randoop-lib/randoop-all-4.2.1.jar' ,'randoop.main.Main' ,'gentests' ,'--testclass=york.eecs.bt.BinaryTree' ,'--output-limit=500']
remove_command = 'rm -rf *.class'
test_command = ['mvn','test','-Dtest=york.eecs.bt.RegressionTest0']
error_test_command = ['mvn','test','-Dtest=york.eecs.bt.ErrorTest0']


code = subprocess.call(clean_command)

if code == 0:
    code_compile = subprocess.call(compile_command)
    
if code_compile == 0:
    randoop_code = subprocess.call(java_command)
    
if randoop_code == 0:
    remove_code = subprocess.call(remove_command, shell = True)
    
Regression_file = 'RegressionTest0.java'
Error_file = 'ErrorTest0.java'
line_to_insert = 'package york.eecs.bt;\n'

REGRESSION_CHECK = os.path.exists(Regression_file)
ERROR_CHECK = os.path.exists(Error_file)

if REGRESSION_CHECK:
    with open(Regression_file,'r+') as file:
        All_lines = file.readlines()
        All_lines.insert(0,line_to_insert)
        file.seek(0)
        file.writelines(All_lines)

if ERROR_CHECK:
    with open(Error_file,'r+') as file:
        All_lines = file.readlines()
        All_lines.insert(0,line_to_insert)
        file.seek(0)
        file.writelines(All_lines)
    
regression_dir = f'./{Regression_file}'
error_dir = f'./{Error_file}'
Final_dir = 'src/test/java/york/eecs/bt'

shutil.copy2(regression_dir,Final_dir)
os.remove(regression_dir)
shutil.copy2(error_dir,Final_dir)
os.remove(error_dir)

test_code = subprocess.call(test_command)

if test_code != 0:
    error_code = subprocess.call(error_test_command)
    
os.chdir('./target/site/jacoco')

if test_code == 0 :
    webbrowser.open('index.html')
