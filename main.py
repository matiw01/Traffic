import cv2
from tracker import *
import os

def rescale_frame(frame, percent=75):
    width = int(frame.shape[1] * percent / 100)
    height = int(frame.shape[0] * percent / 100)
    dim = (width, height)
    return cv2.resize(frame, dim, interpolation=cv2.INTER_AREA)

def main():
    if os.path.exists("objects.csv"):
        os.remove("objects.csv")
    tracker = EuclideanDistTracker()
    cap = cv2.VideoCapture("media\\rondo_inwalidow.mkv")
    object_detector = cv2.createBackgroundSubtractorKNN(history=1000, dist2Threshold=100, detectShadows=False) #cv2.createBackgroundSubtractorKNN(history=1000, dist2Threshold=800, detectShadows=False) #
    while True:
        ret, frame = cap.read()
        if not ret:
            print("error or finished")
            exit(1)
        frame = rescale_frame(frame, percent=50)
        height, width, _ = frame.shape
        x0, x1, y0, y1 = 100, 1000, 100, 900 # z jakiegoś powodu x1 i y1 nie działa
        region_of_interest = frame[y0:y1, x0:x1, :].copy() #cały frame [0:height, 0:width]

        mask = object_detector.apply(region_of_interest)
        _, mask = cv2.threshold(mask, 254, 255, cv2.THRESH_BINARY)
        contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
        detections = []
        for cnt in contours:
            # Calculate area and remove small elements
            area = cv2.contourArea(cnt)
            if area > 20: #musimy miec male area, bo auta są oddalone
                # Draw contours if needed
                cv2.drawContours(region_of_interest, [cnt], -1, (0, 0, 255), )
                x, y, w, h = cv2.boundingRect(cnt)
                detections.append([x, y, w, h])

        # 2. Object Tracking
        boxes_ids = tracker.update(detections)
        for box_id in boxes_ids:
            x, y, w, h, id = box_id
            cv2.putText(region_of_interest, "obj: " + str(id), (x, y - 15), cv2.FONT_HERSHEY_PLAIN, 2, (255, 0, 0), 2)
            cv2.rectangle(region_of_interest, (x, y), (x + w, y + h), (0, 255, 0), 3)

        cv2.putText(frame, 'Detection Region', (50, 290), cv2.FONT_HERSHEY_PLAIN, 1, (0, 255, 0), 1)
        cv2.rectangle(frame, (y0, y1), (x0, x1), (0, 255, 0), 1)
        cv2.imshow("Masked Region", mask)
        cv2.imshow("Detection Region", region_of_interest)
        cv2.imshow("Frame", frame)

        key = cv2.waitKey(1)
        if key == 27: #ESC to exit
            exit(0)

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    main()