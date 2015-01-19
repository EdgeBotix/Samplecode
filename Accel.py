# This code shows how the eBot can use an accelerometer threshhold to determine when to stop

# Copyright (c) 2014, Erik Wilhelm
# All rights reserved.

# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
# 1. Redistributions of source code must retain the above copyright
   # notice, this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
   # notice, this list of conditions and the following disclaimer in the
   # documentation and/or other materials provided with the distribution.
# 3. All advertising materials mentioning features or use of this software
   # must display the following acknowledgement:
   # This product includes software developed by the Edgebotix.
# 4. Neither the name of the SUTD nor Edgebotix nor the
   # names of its contributors may be used to endorse or promote products
   # derived from this software without specific prior written permission.

# THIS SOFTWARE IS PROVIDED BY ERIK WILHELM ''AS IS'' AND ANY
# EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL ERIK WILHELM BE LIABLE FOR ANY
# DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.



import sys
sys.path.insert(0, 'C:\Users\Erik Wilhelm\Documents\GitHub\eBot-API') #Insert your directory for the eBot-API here

from eBot import *
import math

mag = lambda x: math.sqrt(sum(i**2 for i in x))

#Create new instance of eBot - connects to first eBot the computer is connected to
myEBot = eBot()
myEBot.connect()
myEBot.halt()
myvalue = [0, 0, 0]
sleep(1)
Thresh = input("Enter a sum-of-square acceleration threshold above which the robot will stop moving (10 works well): ")
bump=int(Thresh) #above this value the acceleration

myEBot.wheels(1, 1) #set the robot on a Yavin Trench Run mission! Photon Torpedos!

while 1: #evenutally we will hit something
	acc = myEBot.acceleration()
	print mag(acc)
	if mag(acc)>Thresh:
		myEBot.halt()


myEBot.halt()
sleep(4)

myEBot.close()


