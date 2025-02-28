package ru.planet.auth.helper;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import ru.planet.auth.exception.BusinessException;
import ru.planet.auth.exception.ValidationException;

import java.util.function.Supplier;

public class GrpcExecutionHelper {
    public static <T> void executeSingle(StreamObserver<T> observer, Supplier<T> resultSupplier) {
        try {
            observer.onNext(resultSupplier.get());
            observer.onCompleted();
        } catch (BusinessException e) {
            observer.onError(new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage())));
        } catch (ValidationException e) {
            observer.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription(e.getMessage())));
        } catch (Throwable var3) {
            observer.onError(var3);
        }
    }
}
