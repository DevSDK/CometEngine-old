import os
import shutil
print("Copy Resource To Asset Direcotry Plese Wait")

if os.access("assets",os.F_OK) == True:
    shutil.rmtree("assets") 
else:
    os.mkdir("assets")

shutil.copytree(os.getcwd() + "\..\Resource","assets");
print("Copy Complite!")
