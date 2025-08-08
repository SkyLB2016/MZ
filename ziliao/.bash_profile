 
#Android所需配置：
#export PATH=/Applications/Android\ Studio/Contents/jre/jdk/Contents/Home/bin:$PATH
#export PATH=/Applications/Android\ Studio/Contents/gradle/gradle-5.1.1/bin/:$PATH
#export PATH=/Users/sky/Library/Android/sdk/platform-tools/:$PATH
#export PATH=tools/:$PATH
#Android所需配置


JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home
#JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home


#JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home
#JAVA_HOME=/Applications/Android\ Studio.app/Contents/jbr/Contents/Home

ANDROID_HOME=/Users/sky/Library/Android/sdk
GRADLE_HOME=/Users/sky/.gradle/wrapper/dists/gradle-7.2-bin/2dnblmf4td7x66yl1d74lt32g/gradle-7.2

GROOVY_HOME=/Users/sky/.groovy-3.0.9

FLUTTER=/Users/sky/Library/Android/flutter

#本地Maven仓库配置
MAVEN_HOME=/usr/local/apache-maven-3.9.5

#貌似不需要配。
#PYTHON=/usr/local/bin/python3
#PIP=/Library/Frameworks/Python.framework/Versions/3.12/lib/python3.12/site-packages
#:$PYTHON:$PIP/pip

PATH=$PATH:$JAVA_HOME/bin:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$GRADLE_HOME/bin:$FLUTTER/bin:$GROOVY_HOME/bin:$MAVEN_HOME/bin:$ANDROID_HOME/build-tools/28.0.3

export JAVA_HOME
export ANDROID_HOME
export GRADLE_HOME
export FLUTTER
export GROOVY_HOME
export MAVEN_HOME
export PATH
export OBJC_DISABLE_INITIALIZE_FORK_SAFETY=YES


#export LANG="en_US.UTF-8"
#export LC_ALL="en_US.UTF-8"

# >>> conda initialize >>>
# !! Contents within this block are managed by 'conda init' !!
__conda_setup="$('/Users/sky/anaconda3/bin/conda' 'shell.bash' 'hook' 2> /dev/null)"
if [ $? -eq 0 ]; then
    eval "$__conda_setup"
else
    if [ -f "/Users/sky/anaconda3/etc/profile.d/conda.sh" ]; then
        . "/Users/sky/anaconda3/etc/profile.d/conda.sh"
    else
        export PATH="/Users/sky/anaconda3/bin:$PATH"
    fi
fi
unset __conda_setup
# <<< conda initialize <<<

___MY_VMOPTIONS_SHELL_FILE="${HOME}/.jetbrains.vmoptions.sh"; if [ -f "${___MY_VMOPTIONS_SHELL_FILE}" ]; then . "${___MY_VMOPTIONS_SHELL_FILE}"; fi

