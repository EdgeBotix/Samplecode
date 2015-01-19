#this code puts the eBot in wander mode, where it tries to minimize distance from obstacles

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
   # This product includes software developed by the <organization>.
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


# A simple program that wanders and avoids obstacles

from finch import Finch
from time import sleep

# Instantiate the Finch object and connect to Finch
tweety = Finch()

# Get the Z-Axis acceleration
zAccel = tweety.acceleration()[2]

# Do the following while the Finch is not upside down (z value in gees above -0.7)
while zAccel > -0.7:
    
    left_obstacle, right_obstacle = tweety.obstacle()
    # If there's an obstacle on the left, back up and arc
    if left_obstacle:
        tweety.led(255,0,0)
        tweety.wheels(-0.3,-1.0)
        sleep(1.0)
    # Back up and arc in the opposite direction if there's something on the right
    elif right_obstacle:
        tweety.led(255,255,0)
        tweety.wheels(-1.0, -0.3)
        sleep(1.0)
    # Else just go straight
    else:
        tweety.wheels(1.0, 1.0)
        tweety.led(0,255,0)
    # Keep reading in the Z acceleration
    zAccel = tweety.acceleration()[2]
    
tweety.close()
