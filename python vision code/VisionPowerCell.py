#this is ythe vision code for the power cells 

import cv2
import numpy as np

def VisionCode(frame):
    lower = (18,30,105)
    upper = (35,255,255)
    fovhoriz = 62.2
    fovvert = 48.8
    hsvframe = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    mask = cv2.inRange(hsvframe, lower, upper)
    contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE) #please fix
    area = 1000
    contour = False
    for cnt in contours:
        if cv2.contourArea(cnt) > area and cv2.contourArea(cnt) > 1000:
            contour = cnt
            area = cv2.contourArea(cnt)
    if contour is not False:
        hasTarget = True
        x, y, w, h = cv2.boundingRect(contour)
        px = Frame.shape[1] #not right?
        py = Frame.shape[0]
        nx = (1/160) * (px - 159.5) #resolution for limelight camera is 320x240- change values for our camera
        ny = (1/120) * (119.5 - py) #(nx,ny) is normalized pixel coordinates (from center instead of top left)
        vpw = 2.0*tan(fovhoriz/2) #view plane width + height
        vph = 2.0*tan(fovvert/2)
        x = vpw/2 * nx #x and y defined in line 22, should we change?
        y = vph/2 * ny
        tan(angleHoriz) = x / 1
        tan(angleVert) = y / 1
        angleHoriz = atan2(1,x)
        angleVert = atan2(1,y)
        #zhoriz = frame.shape[1] * 0.5 / np.tan(np.deg2rad(fovhoriz) * 0.5)
        #zvert = frame.shape[1] * 0.5 / np.tan(np.deg2rad(fovvert) * 0.5)
        #position = x + (w * 0.5) - (frame.shape[1] * 0.5)
        #angleHoriz = np.rad2deg(np.arctan(position / zhoriz))
        #angleVert = np.rad2deg(np.arctan(position / zvert))
        datadict = {
            "Horizontal Angle": angleHoriz,
            "Vertical Angle": angleVert,
            "Has Target": hasTarget
        }
        frame = cv2.rectangle(frame, (x, y), (x+w, y+h), (0,255,0), 2)
    else:
        hasTarget = False
        datadict = {
            "Has Target": hasTarget
        } 
    return datadict
    pass

if __name__ == '__main__':
    global cap
    try:
        cap.release(0)
    except:
        pass
    cv2.destroyAllWindows()
    cap = cv2.VideoCapture(0)
    cap.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
    cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)

    k=255
    while k !=27:
        ret, frame = cap.read()
        if ret:
            outputs = VisionCode(frame)
            if outputs is not None:
                print(outputs)
            cv2.imshow('image', frame)
            k = cv2.waitKey(1) & 0xFF
            if k == 27:
                break
    cap.release()
    cv2.destroyAllWindows()
    