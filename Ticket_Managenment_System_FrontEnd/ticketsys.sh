# Repetative script code isnt commented
#!/bin/bash
#This is the automated bash file that runs in the terminal buy typing ./ticketsys.sh
#The Script executes the test cases in following order:
#login,logout,create,refund, addcredit, buy, delete,sell, quit
#
#The script code for all the folder is repetative for login,logout, quit
#The script code for all the folder is repetative for create,refund, addcredit, buy, delete,sell
#-------------------------------------------------------------------------------------------------
# Script Variable Definations
# Variable:
#    file       : is used used to store an integer value of the number of files that are in the
#		  TestCasesOrganization/login/login directory
#    file1      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/logout/
#    file2      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/create/
#    file3      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/refund/
#    file5      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/addcredit/
#    file6      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/buy/
#    file7      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/delete/
#    file8      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/sell/
#    file4      : is used to store an integer value of the number of files that are in the 
#   		  TestCasesOrganization/quit/
#    esc        : is used to 
#    cc_red     : setting the colour of the failed testcases to red
#    cc_green   : setting the colour of the passed testcases to green
#    cc_normal  : setting any other outputs from the script to have normal colour (grey)
#    WORKINGDIR : is used to store the working directory of the current computer which is used 
#		  to makes sure that the files are being removed and copied correctly 
#-------------------------------------------------------------------------------------------------


# Set colors
# Escape code
esc=`echo -en "\033"`
cc_red="${esc}[0;31m"
cc_green="${esc}[0;32m"
cc_normal=`echo -en "${esc}[m\017"`
WORKINGDIR=$(pwd)

#runs the make file to create the executable file
make
#it removes the old log file if it's already there
rm log.txt
#start of the login test cases
echo "-----------------------Login Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/* 
for file in TestCasesOrganization/login/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
./ticket <$file/*.inp &>$file/term.out
# comparing the expected output(.bot) with actual output(term.out) and assigns it as DIFF
DIFF=$(diff -a $file/*.bto $file/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF" == "" ]
then
echo "${cc_green}$file/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF" != "" ]
then
echo "${cc_red}$file${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file/*.bto $file/term.out >>log.txt
fi
#end of the login test
 echo "------------------------------------------------------------">>log.txt
done

#start of the logout test cases
echo "-----------------------Logout Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file1 in TestCasesOrganization/logout/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
 ./ticket <$file1/*.inp &>$file1/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF

 DIFF1=$(diff -a $file1/*.bto $file1/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF1" == "" ]
then
echo "${cc_green}$file1/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file1/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF1" != "" ]
then
echo "${cc_red}$file1${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file1" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file1/*.bto $file1/term.out >>log.txt
fi
 echo "------------------------------------------------------------">>log.txt
done


echo "-----------------------Create Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file2 in TestCasesOrganization/create/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
 ./ticket <$file2/*.inp &>$file2/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF

 DIFF2=$(diff -a $file2/*.bto $file2/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF2" == "" ]
then
echo "${cc_green}$file2/${cc_normal}"
echo "${cc_green}Test Sucessfull${cc_normal}"
echo "$file2/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF2" != "" ]
then
echo "${cc_red}$file2${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file2" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file2/*.bto $file2/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file2/out.ourdtf
#copies the DTF to correct directory
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file2/out.ourdtf
#differenciating expacted dtf with actual dtf
DTF=$(diff -a $file2/*.dtf $file2/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF" == "" ]
then
echo "${cc_green}$file2/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
if [ "$DTF" != "" ]
then
echo "${cc_red}$file2${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi

 echo "------------------------------------------------------------">>log.txt
done



echo "-----------------------Refund Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file3 in TestCasesOrganization/refund/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system

 ./ticket <$file3/*.inp &>$file3/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF
 DIFF3=$(diff -a $file3/*.bto $file3/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file

if   [ "$DIFF3" == "" ]
then
echo "${cc_green}$file3/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file3/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file

if [ "$DIFF3" != "" ]
then
echo "${cc_red}$file3${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file3" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file3/*.bto $file3/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file3/out.ourdtf
#copies the DTF to correct directory
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file3/out.ourdtf
#differenciating expacted dtf with actual dtf
DTF1=$(diff -a $file3/*.dtf $file3/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF1" == "" ]
then
echo "${cc_green}$file3/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
if [ "$DTF1" != "" ]
then
echo "${cc_red}$file3${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi

 echo "------------------------------------------------------------">>log.txt
done

echo "-----------------------Add Credit Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file5 in TestCasesOrganization/addcredit/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
 ./ticket <$file5/*.inp &>$file5/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF
 DIFF5=$(diff -a $file5/*.bto $file5/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF5" == "" ]
then
echo "${cc_green}$file5/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file5/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file

if [ "$DIFF5" != "" ]
then
echo "${cc_red}$file5${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file5" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file5/*.bto $file5/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file5/out.ourdtf
#copies the DTF to correct directory
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file5/out.ourdtf
#differenciating expacted dtf with actual dtf
DTF2=$(diff -a $file2/*.dtf $file2/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF2" == "" ]
then
echo "${cc_green}$file5/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
if [ "$DTF2" != "" ]
then
echo "${cc_red}$file5${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi
 echo "------------------------------------------------------------">>log.txt
done

echo "-----------------------Buy Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file6 in TestCasesOrganization/buy/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
 ./ticket <$file6/*.inp &>$file6/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF
 DIFF6=$(diff -a $file6/*.bto $file6/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF6" == "" ]
then
echo "${cc_green}$file6/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file6/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF6" != "" ]
then
echo "${cc_red}$file6${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file6" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file6/*.bto $file6/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file6/out.ourdtf
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file6/out.ourdtf
#copies the DTF to correct directory
DTF3=$(diff -a $file6/*.dtf $file6/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF3" == "" ]
then
echo "${cc_green}$file6/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
if [ "$DTF3" != "" ]
then
echo "${cc_red}$file6${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi
 echo "------------------------------------------------------------">>log.txt
done

echo "-----------------------delete Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file7 in TestCasesOrganization/delete/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system

 ./ticket <$file7/*.inp &>$file7/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF
 DIFF7=$(diff -a $file7/*.bto $file7/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF7" == "" ]
then
echo "${cc_green}$file7/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file7/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF7" != "" ]
then
echo "${cc_red}$file7${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file7" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file7/*.bto $file7/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file7/out.ourdtf
#copies the DTF to correct directory
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file7/out.ourdtf
DTF4=$(diff -a $file7/*.dtf $file7/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF4" == "" ]
then
echo "${cc_green}$file7/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
if [ "$DTF4" != "" ]
then
echo "${cc_red}$file7${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi
 echo "------------------------------------------------------------">>log.txt
done

echo "-----------------------Sell Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file8 in TestCasesOrganization/sell/*;

do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system

 ./ticket <$file8/*.inp &>$file8/term.out
# comparing the expected output(.bto) with actual output(term.out) and assigns it as DIFF

 DIFF8=$(diff -a $file8/*.bto $file8/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file

if   [ "$DIFF8" == "" ]
then
echo "${cc_green}$file8/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file8/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF8" != "" ]
then
echo "${cc_red}$file8${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file8" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file8/*.bto $file8/term.out >>log.txt
fi
#removes the old out.ourdtf
rm $file8/out.ourdtf
#copies the DTF to correct directory
cp -i $WORKINGDIR/Daily_Transaction_File.dtf $file8/out.ourdtf
#differenciating expacted dtf with actual dtf
DTF5=$(diff -a $file8/*.dtf $file8/out.ourdtf)
#if DTF is empty, then output was sucesssful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
if   [ "$DTF5" == "" ]
then
echo "${cc_green}$file8/${cc_normal}"
echo "${cc_green}DTF Sucessfull${cc_normal}"
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal

if [ "$DTF5" != "" ]
then
echo "${cc_red}$file8${cc_normal}"
echo "${cc_red}DTF Failed${cc_normal}"
fi
 echo "------------------------------------------------------------">>log.txt
done

echo "-----------------------Quit Test-----------------------------">>log.txt
# Everyfile in the TestCasesOrganization/login/*
for file4 in TestCasesOrganization/quit/*;
do
# all the files that contain .inp use it as input for the system
# all the files that contain .out use it as output for the system
 ./ticket <$file4/*.inp &>$file4/term.out
# comparing the expected output(.bot) with actual output(term.out) and assigns it as DIFF
 DIFF4=$(diff -a $file4/*.bto $file4/term.out)
# if DIFF is empty, then output test was sucessful
# Shows the file path in Green along with "TestCase Sucessfull" in terminal
# writes the file path and Test Case Sucessfull to the log.txt file
if   [ "$DIFF4" == "" ]
then
echo "${cc_green}$file4/${cc_normal}"
echo "${cc_green}TestCase Sucessfull${cc_normal}"
echo "$file4/" >>log.txt
echo TestCase Sucessfull>>log.txt
fi
# if DIFF is not empty, then output test was unsucessful
# Shows the file path in Red along with "TestCase Failed" in terminal
# writes the file path and Test Case Failed and the difference in the log.txt file
if [ "$DIFF4" != "" ]
then
echo "${cc_red}$file4${cc_normal}"
echo "${cc_red}TestCase Failed${cc_normal}"
echo "$file4" >>log.txt
echo "Test Case Failed">>log.txt
diff -y $file4/*.bto $file4/term.out >>log.txt
fi
 echo "------------------------------------------------------------">>log.txt
done
#end
